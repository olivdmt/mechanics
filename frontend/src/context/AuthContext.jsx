import { createContext, useContext, useMemo, useState } from 'react'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => localStorage.getItem('username') || '')
  const [token, setToken] = useState(() => localStorage.getItem('token') || '')
  const value = useMemo(() => ({
    user,
    signIn(username, token) {
      localStorage.setItem('token', token)
      localStorage.setItem('username', username)
      setUser(username)
      setToken(token)
    },
    signOut() {
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      setUser('')
      setToken('')
    },
    token,
  }), [user, token])

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
  return useContext(AuthContext)
}
