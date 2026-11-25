import { createGlobalStyle } from 'styled-components'

export const GlobalStyle = createGlobalStyle`
  :root { font-family: Arial, sans-serif; color: #202b38; background: #f5f7f9; font-synthesis: none; }
  * { box-sizing: border-box; }
  body { margin: 0; }
  button, input { font: inherit; }
  button { border: 0; border-radius: 8px; background: #de572c; color: white; font-weight: 700; padding: 12px 17px; cursor: pointer; }
  button:hover { background: #c94720; }
  a { color: #d6532a; text-decoration: none; }
`
