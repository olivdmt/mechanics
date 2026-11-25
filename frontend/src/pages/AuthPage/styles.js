import styled from 'styled-components'

export const AuthShell = styled.main`min-height: 100vh; display: grid; place-items: center; background: radial-gradient(ellipse at 75% 20%, #32434d, #17232c 70%); padding: 20px;`
export const AuthForm = styled.form`width: min(410px, 100%); background: white; border-radius: 15px; padding: 36px; box-shadow: 0 22px 60px #0003; display: grid; gap: 15px; .eyebrow { color: #d6532a; margin: 8px 0 0; font-size: 10px; font-weight: 700; letter-spacing: 1.35px; } h1 { font: 800 25px Manrope; margin: 0; } .muted { color: #87919a; font-size: 12px; margin: 0; } label { display: grid; gap: 6px; font-size: 11px; font-weight: 600; color: #56636c; } input { border: 1px solid #dfe4e6; border-radius: 7px; padding: 10px; outline-color: #df582d; } button { margin-top: 4px; }`
export const BrandMark = styled.div`display: grid; place-items: center; background: #df582d; color: white; width: 40px; height: 40px; border-radius: 11px; font: 800 22px Manrope;`
export const ErrorMessage = styled.p`padding: 12px 15px; background: #fff0ed; color: #a7432c; border-radius: 8px; font-size: 12px; margin: 0;`
