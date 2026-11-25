import styled from 'styled-components'

export const Content = styled.div`max-width: 1320px; padding: 38px; margin: auto; @media (max-width: 950px) { padding: 25px; } @media (max-width: 650px) { padding: 18px; }`
export const Eyebrow = styled.p`font-size: 10px; font-weight: 700; letter-spacing: 1.35px; color: #94a0a9;`
export const PageHeading = styled.div`display: flex; justify-content: space-between; align-items: center; margin: 8px 0 25px; h1 { font: 800 27px Manrope; margin: 5px 0; } p:not(:first-child) { font-size: 12px; color: #87919a; margin: 6px 0 0; } @media (max-width: 650px) { align-items: flex-end; gap: 12px; button { padding: 10px; font-size: 11px; } }`
export const Panel = styled.section`background: white; border: 1px solid #e9edef; border-radius: 11px;`
export const Notice = styled.div`padding: 12px 15px; background: #fff0ed; color: #a7432c; border-radius: 8px; font-size: 12px; margin-bottom: 16px;`
