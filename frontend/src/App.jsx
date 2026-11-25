import { Navigate, Route, Routes } from 'react-router-dom'
import { AuthProvider } from './context/AuthContext'
import { AuthPage } from './pages/AuthPage'
import { DashboardPage } from './pages/DashboardPage'
import { CrudPage } from './pages/CrudPage'
import { AppLayout } from './components/AppLayout'
import { sections } from './data/navigation'
import { useAuth } from './context/AuthContext'

function ProtectedLayout() {
  const { token } = useAuth()
  return token ? <AppLayout /> : <Navigate to="/login" replace />
}

export default function App() {
  return (
    <AuthProvider>
      <Routes>
        <Route path="/" element={<Navigate to="/dashboard" replace />} />
        <Route path="/login" element={<AuthPage />} />
        <Route path="/register" element={<AuthPage register />} />
        <Route element={<ProtectedLayout />}>
          <Route path="/dashboard" element={<DashboardPage />} />
          {sections.slice(1).map(({ path, label }) => (
            <Route key={path} path={path} element={<CrudPage endpoint={path} title={label} />} />
          ))}
        </Route>
      </Routes>
    </AuthProvider>
  )
}
