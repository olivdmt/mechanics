import { useCallback, useEffect, useState } from 'react'
import { api, getApiError } from '../../api/client'
import { apiEndpointByRoute, fieldsByEndpoint, idFieldByEndpoint } from '../../data/navigation'
import { Content, Eyebrow, Notice, PageHeading, Panel } from '../../components/PageLayout/styles'
import { ConfirmOverlay, DataTable, Modal, TableTools } from './styles'

const numericFields = ['quantidade', 'preco', 'ano', 'quilometragem', 'servicoRealizado', 'maoObra', 'valorTotal']
const optionalFields = ['diagnostico', 'historico', 'motivo', 'valorTotal']

function getFieldValue(item, field) {
  const value = item[field]
  if (value && typeof value === 'object') {
    return value.nome || value.placa || value.pecas || value.nomeFantasia || value.idCliente || '—'
  }
  return value ?? '—'
}

function toFormValue(value, type) {
  if (value == null) return ''
  if (type === 'datetime-local') return String(value).slice(0, 16)
  return value
}

export function CrudPage({ endpoint, title }) {
  const [rows, setRows] = useState([])
  const [busy, setBusy] = useState(true)
  const [saving, setSaving] = useState(false)
  const [error, setError] = useState('')
  const [editing, setEditing] = useState(null)
  const [confirming, setConfirming] = useState(null)
  const [deleting, setDeleting] = useState(false)
  const [query, setQuery] = useState('')
  const [options, setOptions] = useState({})
  const fields = fieldsByEndpoint[endpoint] || []
  const idField = idFieldByEndpoint[endpoint]
  const apiEndpoint = apiEndpointByRoute[endpoint] || endpoint

  const load = useCallback(async () => {
    setBusy(true)
    setError('')
    try {
      const { data } = await api.get(apiEndpoint)
      setRows(Array.isArray(data) ? data : [])
    } catch (requestError) {
      setError(getApiError(requestError))
    } finally {
      setBusy(false)
    }
  }, [apiEndpoint])

  useEffect(() => { load() }, [load])

  useEffect(() => {
    const relatedEndpoints = [...new Set(fields.filter((field) => field[2] === 'select').map((field) => field[3]))]
    if (!relatedEndpoints.length) return
    Promise.all(relatedEndpoints.map(async (relatedEndpoint) => {
      try {
        const { data } = await api.get(relatedEndpoint)
        return [relatedEndpoint, Array.isArray(data) ? data : []]
      } catch {
        return [relatedEndpoint, []]
      }
    })).then((entries) => setOptions(Object.fromEntries(entries)))
  }, [fields])

  async function save(event) {
    event.preventDefault()
    setSaving(true)
    setError('')
    const form = new FormData(event.currentTarget)
    const body = Object.fromEntries(form.entries())
    for (const [key, value] of Object.entries(body)) {
      if (numericFields.includes(key) && value !== '') body[key] = Number(value)
      if (['abertura', 'dataHora'].includes(key) && value) body[key] = value.length === 16 ? `${value}:00` : value
      if (value === '' && optionalFields.includes(key)) body[key] = null
    }
    try {
      if (editing[idField]) await api.put(`${apiEndpoint}/${editing[idField]}`, body)
      else await api.post(apiEndpoint, body)
      setEditing(null)
      await load()
    } catch (requestError) {
      setError(getApiError(requestError))
    } finally {
      setSaving(false)
    }
  }

  async function confirmDelete() {
    if (!confirming) return
    setDeleting(true)
    setError('')
    try {
      await api.delete(`${apiEndpoint}/${confirming[idField]}`)
      setConfirming(null)
      await load()
    } catch (requestError) {
      setError(getApiError(requestError))
    } finally {
      setDeleting(false)
    }
  }

  const visibleRows = rows.filter((item) => JSON.stringify(item).toLowerCase().includes(query.toLowerCase()))

  return (
    <Content>
      <PageHeading>
        <div><Eyebrow>CADASTROS</Eyebrow><h1>{title}</h1><p>Consulte e gerencie os registros da oficina.</p></div>
        <button onClick={() => setEditing({})}>＋ Novo cadastro</button>
      </PageHeading>
      {error && editing === null && <Notice role="alert">{error}</Notice>}
      <Panel>
        <TableTools>
          <input placeholder="⌕  Buscar registros..." value={query} onChange={(event) => setQuery(event.target.value)} />
          <span>{rows.length} registros</span>
        </TableTools>
        {busy ? <div className="empty">Carregando dados…</div> : (
          <DataTable>
            <table>
              <thead><tr>{fields.map((field) => <th key={field[0]}>{field[1]}</th>)}<th>Ações</th></tr></thead>
              <tbody>
                {visibleRows.map((item, index) => (
                  <tr key={item[idField] || index}>
                    {fields.map(([key]) => <td key={key}>{String(getFieldValue(item, key))}</td>)}
                    <td><button className="action" onClick={() => setEditing(item)}>Editar</button><button className="action danger" onClick={() => { setError(''); setConfirming(item) }}>Excluir</button></td>
                  </tr>
                ))}
              </tbody>
            </table>
            {!visibleRows.length && <div className="empty">Nenhum registro encontrado.</div>}
          </DataTable>
        )}
      </Panel>
      {editing !== null && (
        <Modal onSubmit={save}>
          <section className="dialog" aria-labelledby="form-title">
            <header className="dialog-header">
              <div className="dialog-heading">
                <span className="dialog-icon">{editing[idField] ? '✎' : '＋'}</span>
                <div><Eyebrow>{editing[idField] ? 'ATUALIZAÇÃO' : 'NOVO REGISTRO'}</Eyebrow><h2 id="form-title">{editing[idField] ? 'Editar' : 'Cadastrar'} {title.toLowerCase()}</h2><p>Preencha os dados abaixo para {editing[idField] ? 'atualizar este registro' : 'adicionar à oficina'}.</p></div>
              </div>
              <button type="button" className="close" aria-label="Fechar formulário" onClick={() => setEditing(null)}>×</button>
            </header>
            {error && <Notice role="alert">{error}</Notice>}
            <div className="fields">
              {fields.map(([key, label, type = 'text', relatedEndpoint, relatedId, relatedLabel]) => (
                <label className={['diagnostico', 'historico', 'motivo'].includes(key) ? 'wide' : ''} key={key}>
                  <span>{label}{!optionalFields.includes(key) && <i aria-hidden="true">*</i>}</span>
                  {type === 'select' ? (
                    <select name={key} defaultValue={editing[key]?.[relatedId] || editing[key] || ''} required>
                      <option value="">Selecione uma opção</option>
                      {(options[relatedEndpoint] || []).map((option) => <option key={option[relatedId]} value={option[relatedId]}>{option[relatedLabel] || option[relatedId]}</option>)}
                    </select>
                  ) : type === 'select-values' ? (
                    <select name={key} defaultValue={editing[key] || ''} required><option value="">Selecione uma opção</option>{relatedEndpoint.map((option) => <option key={option} value={option}>{option}</option>)}</select>
                  ) : ['diagnostico', 'historico', 'motivo'].includes(key) ? (
                    <textarea name={key} rows={3} defaultValue={editing[key] || ''} required={false} placeholder={`Descreva ${label.toLowerCase()}`} />
                  ) : (
                    <input name={key} type={type} step={type === 'number' ? 'any' : undefined} defaultValue={toFormValue(editing[key], type)} required={!optionalFields.includes(key)} placeholder={type === 'datetime-local' ? undefined : `Informe ${label.toLowerCase()}`} />
                  )}
                </label>
              ))}
            </div>
            <footer className="actions"><span className="required-note"><i>*</i> Campos obrigatórios</span><div><button type="button" className="secondary" onClick={() => setEditing(null)}>Cancelar</button><button className="submit" disabled={saving}>{saving ? <><span className="spinner" /> Salvando…</> : editing[idField] ? 'Salvar alterações' : 'Criar cadastro'}</button></div></footer>
          </section>
        </Modal>
      )}
      {confirming && (
        <ConfirmOverlay role="presentation" onMouseDown={(event) => {
          if (event.target === event.currentTarget && !deleting) setConfirming(null)
        }}>
          <section className="confirm-dialog" role="alertdialog" aria-modal="true" aria-labelledby="delete-title" aria-describedby="delete-description">
            <span className="confirm-icon" aria-hidden="true">!</span>
            <p className="eyebrow">CONFIRMAÇÃO</p>
            <h2 id="delete-title">Excluir este registro?</h2>
            <p className="description" id="delete-description">Esta ação remove o item de <strong>{title.toLowerCase()}</strong> da oficina. Deseja continuar?</p>
            <span className="record-name">{confirming.nome || confirming.placa || confirming.pecas || confirming.nomeFantasia || confirming[idField]}</span>
            {error && <Notice role="alert">{error}</Notice>}
            <div className="confirm-actions">
              <button className="cancel" disabled={deleting} onClick={() => setConfirming(null)}>Cancelar</button>
              <button className="delete" disabled={deleting} onClick={confirmDelete}>{deleting ? 'Excluindo…' : 'Sim, excluir'}</button>
            </div>
          </section>
        </ConfirmOverlay>
      )}
    </Content>
  )
}
