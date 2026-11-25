import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { api, getApiError } from '../../api/client'
import { useAuth } from '../../context/AuthContext'
import { AuthForm, AuthShell, BrandMark } from './styles'

export function AuthPage({ register = false }) {
  const navigate = useNavigate()
  const { signIn } = useAuth()
  const [error, setError] = useState('')
  const [busy, setBusy] = useState(false)

  async function submit(event) {
    event.preventDefault()
    setError('')
    setBusy(true)
    const values = Object.fromEntries(new FormData(event.currentTarget))
    try {
      if (register) {
        await api.post('/auth/register', values)
        navigate('/login', { state: { notice: 'Conta criada. Entre com seu usuário e senha.' } })
      } else {
        const { data } = await api.post('/auth/login', values)
        signIn(values.username, data.token)
        navigate('/dashboard')
      }
    } catch (requestError) {
      setError(getApiError(requestError))
    } finally {
      setBusy(false)
    }
  }

  return (
    <AuthShell><AuthForm onSubmit={submit}>
      <BrandMark>M</BrandMark><p className="eyebrow">MECHANICS · GESTÃO DE OFICINA</p>
      <h1>{register ? 'Crie sua conta' : 'Bem-vindo de volta'}</h1>
      <p className="muted">{register ? 'Cadastre seu acesso à oficina.' : 'Entre para acompanhar sua oficina.'}</p>
      <label>Usuário<input name="username" required /></label>
      {register && <label>E-mail<input name="email" type="email" required /></label>}
      <label>Senha<input name="senha" type="password" required /></label>
      {error && <div role="alert" className="error">{error}</div>}
      <button disabled={busy}>{busy ? 'Aguarde…' : register ? 'Criar conta' : 'Entrar'}</button>
      <p className="muted">{register ? 'Já possui acesso? ' : 'Ainda não tem acesso? '}<Link to={register ? '/login' : '/register'}>{register ? 'Entrar' : 'Criar conta'}</Link></p>
    </AuthForm></AuthShell>
  )
}
