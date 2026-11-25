import styled from 'styled-components'

export const TableTools = styled.div`
    display: flex; 
    justify-content: space-between; 
    align-items: center; 
    padding: 16px 20px; 
    border-bottom: 1px solid #edf0f1; 
    color: #87919a; 
    font-size: 11px; 
        input { 
            border: 1px solid #e4e8ea; 
            border-radius: 7px; 
            padding: 10px 12px; 
            width: 280px; 
            outline-color: #df582d; 
        }
`;

export const DataTable = styled.div`
    overflow: auto; 
    table { 
        width: 100%; 
        border-collapse: collapse; 
        text-align: left; 
        font-size: 12px; 
    } 
    th { 
        background: #fafbfb; 
        color: #87919a; 
        font-size: 10px; 
        text-transform: uppercase; 
        letter-spacing: .6px; 
    } 
    td, th { 
        padding: 13px 16px; 
        border-bottom: 1px solid #eff1f2; 
        white-space: nowrap; } 
    td { 
        color: #44515b; 
    } 
    button.action { 
        background: transparent; 
        color: #d6532a; 
        padding: 5px 7px; 
        font-size: 11px; 
    } 
    button.danger { 
        color: #a84c40; 
    } 
    .empty { 
        text-align: center; 
        padding: 50px; 
        color: #8a959c; 
        font-size: 13px; 
    }
`;

export const Modal = styled.form`
  position: fixed;
  inset: 0;
  z-index: 20;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 24px;
  display: grid;
  place-items: center;
  overflow: auto;
  border: 0;
  border-radius: 0;
  background: rgba(15, 27, 35, 0.64);
  backdrop-filter: blur(5px);

  .dialog {
    position: relative;
    width: min(760px, 100%);
    max-height: min(820px, calc(100vh - 48px));
    display: flex;
    flex-direction: column;
    overflow: hidden;
    border: 1px solid rgba(224, 230, 232, 0.9);
    border-radius: 18px;
    background: #fff;
    box-shadow: 0 28px 90px rgba(12, 25, 34, 0.3);
    animation: dialog-enter 180ms ease-out;
  }

  @keyframes dialog-enter {
    from { opacity: 0; transform: translateY(10px) scale(.99); }
    to { opacity: 1; transform: translateY(0) scale(1); }
  }

  .dialog-header {
    position: relative;
    padding: 27px 32px 24px;
    border-bottom: 1px solid #edf0f1;
    background: linear-gradient(135deg, #fff 55%, #fff6f2);
  }

  .dialog-heading { display: flex; align-items: flex-start; gap: 15px; padding-right: 38px; }
  .dialog-icon {
    flex: 0 0 42px;
    width: 42px;
    height: 42px;
    display: grid;
    place-items: center;
    border-radius: 12px;
    color: #d6532a;
    background: #fff0e9;
    font-size: 21px;
    font-weight: 600;
  }
  .dialog-header .eyebrow { margin: 1px 0 5px; color: #d6532a; }
  .dialog-header h2 { margin: 0; color: #202b38; font: 800 21px Manrope, Arial, sans-serif; text-transform: capitalize; }
  .dialog-header p:not(.eyebrow) { margin: 7px 0 0; color: #849098; font-size: 12px; }
  .close {
    position: absolute;
    top: 20px;
    right: 22px;
    width: 34px;
    height: 34px;
    display: grid;
    place-items: center;
    padding: 0;
    border: 1px solid #e9edef;
    border-radius: 9px;
    background: #fff;
    color: #69767e;
    font-size: 22px;
    font-weight: 400;
    transition: background .15s, color .15s;
  }
  .close:hover { background: #f4f6f6; color: #26343e; }
  [role='alert'] { margin: 16px 28px 0; }

  .fields {
    display: grid;
    grid-template-columns: repeat(2, minmax(0, 1fr));
    gap: 18px 20px;
    padding: 25px 32px 29px;
    overflow-y: auto;
  }
  .fields label { min-width: 0; display: grid; align-content: start; gap: 8px; color: #45535d; font-size: 12px; font-weight: 600; }
  .fields label > span { display: flex; align-items: center; gap: 4px; }
  .fields label i, .required-note i { color: #d6532a; font-style: normal; }
  .fields input, .fields select, .fields textarea {
    width: 100%;
    min-width: 0;
    border: 1px solid #dce3e6;
    border-radius: 9px;
    outline: none;
    background: #fbfcfc;
    color: #26343e;
    font: 400 13px 'DM Sans', Arial, sans-serif;
    transition: border-color .15s, box-shadow .15s, background .15s;
  }
  .fields input, .fields select { height: 43px; padding: 0 12px; }
  .fields textarea { min-height: 82px; padding: 11px 12px; resize: vertical; }
  .fields input::placeholder, .fields textarea::placeholder { color: #a6afb4; }
  .fields input:focus, .fields select:focus, .fields textarea:focus { border-color: #dc6845; background: #fff; box-shadow: 0 0 0 3px rgba(220, 104, 69, .12); }
  .fields .wide { grid-column: 1 / -1; }

  .actions {
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 14px;
    padding: 17px 32px;
    border-top: 1px solid #edf0f1;
    background: #fbfcfc;
  }
  .actions > div { display: flex; align-items: center; gap: 9px; }
  .required-note { color: #89949a; font-size: 11px; }
  .actions button { min-height: 40px; padding: 10px 15px; font-size: 12px; }
  .actions button.secondary { border: 1px solid #e2e7e9; background: #fff; color: #53616a; }
  .actions button.secondary:hover { background: #f3f5f5; }
  .actions button.submit { display: inline-flex; align-items: center; justify-content: center; gap: 8px; min-width: 145px; }
  .actions button:disabled { cursor: wait; opacity: .7; }
  .spinner { width: 13px; height: 13px; border: 2px solid rgba(255,255,255,.5); border-top-color: #fff; border-radius: 50%; animation: spin .7s linear infinite; }
  @keyframes spin { to { transform: rotate(360deg); } }

  @media (max-width: 650px) {
    padding: 10px;
    .dialog { max-height: calc(100vh - 20px); border-radius: 14px; }
    .dialog-header { padding: 22px 20px 19px; }
    .dialog-heading { gap: 11px; }
    .dialog-icon { flex-basis: 37px; width: 37px; height: 37px; }
    .dialog-header h2 { font-size: 18px; }
    .close { top: 13px; right: 13px; width: 31px; height: 31px; }
    .fields { grid-template-columns: 1fr; gap: 14px; padding: 20px; }
    .fields .wide { grid-column: auto; }
    .actions { align-items: stretch; padding: 14px 20px; }
    .required-note { display: none; }
    .actions > div { width: 100%; justify-content: flex-end; }
    .actions button.submit { min-width: 0; }
  }
`

export const ConfirmOverlay = styled.div`
  position: fixed;
  inset: 0;
  z-index: 30;
  display: grid;
  place-items: center;
  padding: 20px;
  background: rgba(15, 27, 35, 0.64);
  backdrop-filter: blur(5px);

  .confirm-dialog {
    width: min(430px, 100%);
    padding: 28px;
    border: 1px solid #e8edef;
    border-radius: 16px;
    background: #fff;
    box-shadow: 0 28px 90px rgba(12, 25, 34, 0.3);
    animation: confirm-enter 160ms ease-out;
  }

  @keyframes confirm-enter {
    from { opacity: 0; transform: translateY(8px) scale(.99); }
    to { opacity: 1; transform: translateY(0) scale(1); }
  }

  .confirm-icon {
    width: 44px;
    height: 44px;
    display: grid;
    place-items: center;
    margin-bottom: 18px;
    border-radius: 12px;
    background: #fff0ed;
    color: #bd4a35;
    font-size: 21px;
  }

  .eyebrow { margin: 0 0 6px; color: #bd4a35; font-size: 10px; font-weight: 700; letter-spacing: 1.2px; }
  h2 { margin: 0; color: #202b38; font: 800 19px Manrope, Arial, sans-serif; }
  p.description { margin: 9px 0 0; color: #77848c; font-size: 13px; line-height: 1.6; }
  .record-name { display: block; margin-top: 12px; padding: 10px 12px; overflow-wrap: anywhere; border: 1px solid #edf0f1; border-radius: 8px; background: #fafbfb; color: #33414b; font-size: 12px; font-weight: 700; }
  .confirm-actions { display: flex; justify-content: flex-end; gap: 9px; margin-top: 24px; }
  .confirm-actions button { min-height: 39px; padding: 10px 14px; font-size: 12px; }
  .confirm-actions .cancel { border: 1px solid #e2e7e9; background: white; color: #53616a; }
  .confirm-actions .cancel:hover { background: #f3f5f5; }
  .confirm-actions .delete { background: #bd4a35; }
  .confirm-actions .delete:hover { background: #a63d2c; }
  .confirm-actions button:disabled { cursor: wait; opacity: .65; }

  @media (max-width: 480px) {
    padding: 14px;
    .confirm-dialog { padding: 22px; }
  }
`
