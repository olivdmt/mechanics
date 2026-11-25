import { useEffect, useState } from 'react'
import { NavLink } from 'react-router-dom'
import { api, getApiError } from '../../api/client'
import { sections } from '../../data/navigation'
import { Content, Eyebrow, Panel } from '../../components/PageLayout/styles'
import { StatCard } from '../../components/StatCard'
import { DashboardDetails, DashboardHero, Footnote, QuickLinks, StatsGrid } from './styles'

export function DashboardPage() {
  const [counts, setCounts] = useState({})
  const [error, setError] = useState('')

  useEffect(() => {
    let active = true
    Promise.all(sections.slice(1, 5).map(async ({ path }) => {
      try {
        const { data } = await api.get(path)
        return [path, Array.isArray(data) ? data.length : 0]
      } catch (requestError) {
        if (requestError.response?.status === 401) throw requestError
        return [path, '—']
      }
    })).then((entries) => {
      if (active) setCounts(Object.fromEntries(entries))
    }).catch((requestError) => {
      if (active) setError(getApiError(requestError))
    })
    return () => { active = false }
  }, [])

  return (
    <Content>
      <DashboardHero><div><Eyebrow>OPERAÇÃO DA OFICINA</Eyebrow><h1>Visão geral</h1><p>Acompanhe os principais dados da sua oficina em um só lugar.</p></div><span>⚙</span></DashboardHero>
      {error && <Panel role="alert">{error}</Panel>}
      <StatsGrid>{sections.slice(1, 5).map(({ path, label, icon }) => <StatCard key={path} label={label} value={counts[path] ?? '…'} icon={icon} />)}</StatsGrid>
      <Panel><DashboardDetails><h2>Acesso rápido</h2><p>Gerencie os recursos da oficina</p></DashboardDetails><QuickLinks>{sections.slice(1).map(({ path, label, icon }) => <NavLink to={path} key={path}><i>{icon}</i><span>{label}</span><b>→</b></NavLink>)}</QuickLinks></Panel>
      <Footnote>Mechanics · Sistema de gestão para oficinas mecânicas</Footnote>
    </Content>
  )
}
