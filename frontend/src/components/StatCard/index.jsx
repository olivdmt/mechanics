import { Card } from './styles'

export function StatCard({ label, value, icon }) {
  return <Card><div><span>{label}</span><b>{value}</b><small>registros cadastrados</small></div><i>{icon}</i></Card>
}
