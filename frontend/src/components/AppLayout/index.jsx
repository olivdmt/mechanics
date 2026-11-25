import { NavLink, Outlet, useLocation, useNavigate } from 'react-router-dom'
import { sections } from '../../data/navigation'
import { useAuth } from '../../context/AuthContext'
import { Avatar, Header, Layout, Logo, Main, NavLabel, UserBar } from './styles'

export function AppLayout() {
  const { user, signOut } = useAuth()
  const navigate = useNavigate()
  const location = useLocation()
  const section = sections.find(({ path }) => path === location.pathname)

  function logout() {
    signOut()
    navigate('/login')
  }

  return (
    <Layout>
      <aside>
        <Logo><span>M</span><div>MECHANICS<small>OFICINA & SERVIÇOS</small></div></Logo>
        <NavLabel>MENU PRINCIPAL</NavLabel>
        <nav>{sections.map(({ path, label, icon }) => <NavLink end={path === '/'} key={path} to={path}><b>{icon}</b>{label}</NavLink>)}</nav>
        <UserBar><Avatar>{(user || 'M')[0].toUpperCase()}</Avatar><div>{user || 'Mecânico'}<small>Administrador</small></div><button onClick={logout}>↗</button></UserBar>
      </aside>
      <Main>
        <Header><span>Oficina / <b>{section?.label || 'Painel'}</b></span><div><span>● Sistema online</span><span>{new Date().toLocaleDateString('pt-BR', { day: '2-digit', month: 'long', year: 'numeric' })}</span></div></Header>
        <Outlet />
      </Main>
    </Layout>
  )
}
