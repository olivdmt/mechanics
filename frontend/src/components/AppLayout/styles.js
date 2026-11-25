import styled from 'styled-components'

export const Layout = styled.div`
  min-height: 100vh;
  aside { position: fixed; inset: 0 auto 0 0; width: 250px; background: #17232c; color: #edf3f4; padding: 27px 18px; display: flex; flex-direction: column; }
  nav { display: grid; gap: 5px; }
  nav a { display: flex; align-items: center; gap: 13px; color: #b4c0c4; padding: 12px 13px; border-radius: 8px; font-size: 13px; }
  nav a.active, nav a:hover { background: #293740; color: white; }
  nav a.active { box-shadow: inset 3px 0 #e05b31; }
  @media (max-width: 950px) { aside { width: 205px; } }
  @media (max-width: 650px) { aside { position: static; width: auto; padding: 13px 15px; } nav { display: flex; overflow: auto; margin-top: 12px; } nav a { flex: none; padding: 8px 10px; font-size: 11px; } nav a b { display: none; } }
`
export const Logo = styled.div`display: flex; align-items: center; gap: 12px; font: 800 15px Manrope; letter-spacing: 1.3px; span { display: grid; place-items: center; background: #df582d; color: white; width: 40px; height: 40px; border-radius: 11px; font: 800 22px Manrope; } small { display: block; color: #97a7ad; font: 500 9px 'DM Sans'; letter-spacing: 1.4px; margin-top: 4px; }`
export const NavLabel = styled.p`font-size: 10px; font-weight: 700; letter-spacing: 1.35px; color: #94a0a9; margin: 47px 10px 12px;`
export const UserBar = styled.div`margin-top: auto; border-top: 1px solid #34414a; padding: 17px 4px 0; display: flex; align-items: center; gap: 10px; font-size: 12px; small { display: block; color: #97a7ad; margin-top: 4px; }`
export const Avatar = styled.span`display: grid; place-items: center; border-radius: 50%; width: 35px; height: 35px; background: #37434a; color: white;`
export const Main = styled.main`margin-left: 250px; min-height: 100vh; @media (max-width: 950px) { margin-left: 205px; } @media (max-width: 650px) { margin-left: 0; }`
export const Header = styled.header`height: 64px; background: white; border-bottom: 1px solid #e9edef; padding: 0 38px; display: flex; align-items: center; justify-content: space-between; font-size: 12px; color: #87919a; > div { display: flex; gap: 22px; align-items: center; } @media (max-width: 650px) { padding: 0 15px; }`
