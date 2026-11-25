export const sections = [
  { path: '/dashboard', label: 'Painel', icon: '⌂' },
  { path: '/clientes', label: 'Clientes', icon: '♙' },
  { path: '/veiculos', label: 'Veículos', icon: '▣' },
  { path: '/OS', label: 'Ordens de serviço', icon: '▤' },
  { path: '/estoque', label: 'Estoque', icon: '◈' },
  { path: '/fornecedores', label: 'Fornecedores', icon: '♧' },
  { path: '/movimentacoes', label: 'Movimentações', icon: '↔' },
]

export const fieldsByEndpoint = {
  '/clientes': [['nome', 'Nome'], ['endereco', 'Endereço'], ['cpfcnpj', 'CPF/CNPJ'], ['historico', 'Histórico'], ['telefone', 'Telefone']],
  '/veiculos': [['cliente', 'Cliente', 'select', '/cliente', 'idCliente', 'nome'], ['placa', 'Placa'], ['modelo', 'Modelo'], ['marca', 'Marca'], ['ano', 'Ano', 'number'], ['quilometragem', 'Quilometragem', 'number']],
  '/OS': [['veiculo', 'Veículo', 'select', '/veiculos', 'idVeiculo', 'placa'], ['abertura', 'Abertura', 'datetime-local'], ['diagnostico', 'Diagnóstico'], ['servicoRealizado', 'Serviço realizado', 'number'], ['maoObra', 'Mão de obra', 'number'], ['valorTotal', 'Valor total', 'number']],
  '/estoque': [['pecas', 'Peça'], ['quantidade', 'Quantidade', 'number'], ['preco', 'Preço', 'number'], ['idFornecedor', 'Fornecedor', 'select', '/fornecedores', 'idFornecedor', 'nomeFantasia']],
  '/fornecedores': [['razaoSocial', 'Razão social'], ['nomeFantasia', 'Nome fantasia'], ['cpfcnpj', 'CPF/CNPJ'], ['email', 'E-mail', 'email'], ['telefone', 'Telefone'], ['cep', 'CEP']],
  '/movimentacoes': [['estoque', 'Peça', 'select', '/estoque', 'idEstoque', 'pecas'], ['tipoMovimentacao', 'Tipo', 'select-values', ['ENTRADA', 'SAIDA', 'AJUSTE']], ['quantidade', 'Quantidade', 'number'], ['dataHora', 'Data e hora', 'datetime-local'], ['motivo', 'Motivo']],
}

export const idFieldByEndpoint = {
  '/clientes': 'idCliente',
  '/veiculos': 'idVeiculo',
  '/OS': 'idOS',
  '/estoque': 'idEstoque',
  '/fornecedores': 'idFornecedor',
  '/movimentacoes': 'idMovimentacao',
}

export const apiEndpointByRoute = {
  '/clientes': '/cliente',
  '/veiculos': '/veiculos',
  '/OS': '/OS',
  '/estoque': '/estoque',
  '/fornecedores': '/fornecedores',
  '/movimentacoes': '/movimentacoes',
}
