package com.jsf22.html5.app.util;
import java.util.Locale;

public interface Constantes {

    /** Nome do arquivo de propriedades. */
    String ARQUIVO_RESOURCE_PROP = "config";

    /** Nome do arquivo de mensagens. */
    String ARQUIVO_RESOURCE_MSG = "messages";

    /** Formato padrão de data brasileira. */
    String PADRAO_DATA_BR = "pattern.data";

    /** Chave para o detalhe de erro de exceção. */
    String CHAVE_USUARIO_AUTENTICADO = "chave.usuario.autenticado";

    /** Chave para o detalhe de erro de exceção. */
    String CHAVE_DETALHE_ERRO_EXCECAO = "msg.erro.exception";

    /** Mensagem de cadastro realizado com sucesso. */
    String MSG_INFO_CADASTRO_REALIZADO_COM_SUCESSO = "msg.info.cadastro_realizado_sucesso";

    /** Mensagem de operação realizada com sucesso. */
    String MSG_INFO_OPERACAO_REALIZADA_COM_SUCESSO = "msg.info.operacao_realizada_sucesso";

    /** Mensagem de informações alteradas com sucesso. */
    String MSG_INFO_INFORMACAO_ALTERADA_COM_SUCESSO = "msg.info.informacoes_alteradas_sucesso";

    /** Mensagem de registro excluído com sucesso. */
    String MSG_INFO_REGISTRO_EXCLUIDO_COM_SUCESSO = "msg.info.registro_excluido_sucesso";

    /** Mensagem informando que os campos com asteríscos são obrigatórios. */
    String MSG_INFO_CAMPOS_ASTERISCOS_OBRIGATORIOS = "msg.info.campos.obrigatorios";

    /** Mensagem de nenhum registro encontrado. */
    String MSG_INFO_NENHUM_REGISTRO_ENCONTRADO = "msg.info.nenhum_registro_encontrado";

    /** Mensagem de erro, registro não existe. */
    String MSG_ERRO_REGISTRO_NAO_EXISTE = "msg.erro.exclusao.registro_nao_existe";

    /** Mensagem de erro interno do sistema. */
    String MSG_ERRO_INTERNO_SISTEMA = "msg.erro.erro_interno_sistema";

    /** Mensagem de erro de conexão com o banco de dados. */
    String MSG_ERRO_CONEXAO_BANCO = "msg.erro.conexao_banco";

    /** Mensagem de erro de violação de restrição. */
    String MSG_ERRO_CONSTRAINT_VIOLATION = "msg.erro.violacao_restricao";

    /** Mensagem de erro de operação bloqueada (<i>lock</i>). */
    String MSG_ERRO_OPERACAO_BLOQUEADA = "msg.erro.operacao_bloqueada";
    
    /** Mensagem de erro de usuário não autenticado */
    String MSG_ERRO_USUARIO_NAO_AUTENTICADO = "msg.erro.usuario_nao_autenticado";

    /** Mensagem de erro em um ou mais event listener. */
    String MSG_ERRO_EVENT_LISTENER = "msg.erro.event.listener";

    /** Mensagem de erro de view expirada. */
    String MSG_ERRO_VIEW_EXPIRADA = "msg.erro.view.expirada";

    /** Mensagem de erro ao redirecionar para uma página. */
    String MSG_ERRO_REDIRECIONAR_PAGINA = "msg.erro.redirecionar.pagina";

    /** Mensagem de erro de banco de dados. */
    String MSG_ERRO_BANCO_DADOS = "msg.erro.banco.dados";

    /** Mensagem de erro ao gerar relatório. */
    String MSG_ERRO_RELATORIO = "msg.erro.gerar.relatorio";

    /** Mensagem de erro de relatório não encontrado. */
    String MSG_ERRO_RELATORIO_NAO_ENCONTRADO = "msg.erro.relatorio.nao.encontrado";

    /** Mensagem de erro ao enviar e-mail. */
    String MSG_ERRO_ENVIAR_EMAIL = "msg.erro.enviar.relatorio";

    /** Mensagem de erro ao realizar download de arquivo. */
    String MSG_ERRO_DOWNLOAD = "msg.erro.download";

    /** Mensagem de objeto nulo. */
    String MSG_ERRO_OBJETO_NULO = "msg.erro.entity_nulo";

    /** Mensagem de campo obrigatório não preenchido. */
    String MSG_ERRO_CAMPO_OBRIGATORIO_NAO_PREENCHIDO = "msg.erro.campo_obrigatorio_nao_preenchido";

    /** Mensagem de erro ao obter valor via reflexão. */
    String MSG_ERRO_REFLEXAO_OBTER_VALOR = "msg.erro.reflexao.obter_valor";

    /** Mensagem de erro ao setar valor via reflexão. */
    String MSG_ERRO_REFLEXAO_SETAR_VALOR = "msg.erro.reflexao.setar_valor";

    /** Mensagem de erro ao instanciar objeto via reflexão. */
    String MSG_ERRO_REFLEXAO_INSTANCIAR_OBJETO = "msg.erro.reflexao.instanciar_objeto";

    /** Mensagem data fim menor que data início. */
    String MSG_ERRO_DATA_FIM_MENOR_DATA_INICIO = "msg.erro.data_fim_menor_data_inicio";

    /** Mensagem de validação de CPF. */
    String MSG_ERRO_VALIDACAO_CPF = "msg.erro.validacao.cpf";

    /** Mensagem de validação de CNPJ. */
    String MSG_ERRO_VALIDACAO_CNPJ = "msg.erro.validacao.cnpj";

    /** Mensagem de validação de PIS. */
    String MSG_ERRO_VALIDACAO_PIS_PASEP = "msg.erro.validacao.pis_pasep";

    /** Mensagem de validação de TITULO ELEITOR. */
    String MSG_ERRO_VALIDACAO_TITULO_ELEITOR = "msg.erro.validacao.titulo.eleitor";

    /** Mensagem de validação de URL. */
    String MSG_ERRO_VALIDACAO_URL = "msg.erro.validacao.url";

    /** Mensagem de validação de E-mail. */
    String MSG_ERRO_VALIDACAO_EMAIL = "msg.erro.validacao.email";

    /** Diretório dos relatórios. */
    String PROP_DIR_RELATORIO = "diretorio.relatorio";

    /** Host do servidor de e-mail. */
    String PROP_EMAIL_SMTP_HOST = "mail.sendmail.server.smtp.host";

    /** Porta usada para envio pelo servidor de e-mail. */
    String PROP_EMAIL_SMTP_PORT = "mail.sendmail.server.smtp.port";

    /** E-mail que aparecerá no campo remetente. */
    String PROP_EMAIL_SMTP_FROM = "mail.sendmail.server.smtp.from";

    /** Tempo limite (em ms) para a conexão. */
    String PROP_EMAIL_SMTP_CONNECTION_TIMEOUT = "mail.sendmail.server.server.smtp.connectiontimeout";

    /** Tempo limite (em ms) para a transmissão. */
    String PROP_EMAIL_SMTP_TIMEOUT = "mail.sendmail.server.smtp.timeout";

    /** Flag que indicar se vai ser usado conexão via servidor de proxy. (<tt>TRUE</tt> ou <tt>FALSE</tt>). */
    String PROP_EMAIL_PROXY = "mail.sendmail.server.proxy";

    /** Host do servidor de proxy. */
    String PROP_EMAIL_PROXY_HOST = "mail.sendmail.server.proxy.host";

    /** Porta usada pelo servidor de proxy. */
    String PROP_EMAIL_PROXY_PORT = "mail.sendmail.server.proxy.port";

    /** Flag que indica se o servidor de e-mail requer conexão segura (<tt>TRUE</tt> ou <tt>FALSE</tt>). */
    String PROP_EMAIL_REQUERID_SECURE_CONNECTION = "mail.sendmail.server.requerid.secure_connection";

    /** Flag que indica se o servidor de e-mail requer autenticação (<tt>TRUE</tt> ou <tt>FALSE</tt>). */
    String PROP_EMAIL_REQUERID_AUTH = "mail.sendmail.server.requerid.auth";

    /** Nome do usuário usado na autenticação no servidor de e-mail. */
    String PROP_EMAIL_REQUERID_AUTH_USERNAME = "mail.sendmail.server.username";

    /** Senha usada na autenticação no servidor de e-mail. */
    String PROP_EMAIL_REQUERID_AUTH_PASSWORD = "mail.sendmail.server.password";

    /** Algorítmo de criptografia usado na assinatura digital. */
    String PROP_ALGORITMO_CRIPTOGRAFIA = "algoritmo.criptografia";
    
    /** Outcome do faces-config.xml referente a página de erro desconhecido da aplicacao */
    String PROP_OUTCOME_ERRO_DESCONHECIDO = "outcome.erroDesconhecido";

    /** Driver do banco de teste. */
    String CHAVE_DB_TESTE_DRIVER = "chave.db.teste.driver";

    /** URL do banco de teste. */
    String CHAVE_DB_TESTE_URL = "chave.db.teste.url";

    /** Nome do schema de teste. */
    String CHAVE_DB_TESTE_SCHEMA = "chave.db.teste.schema";

    /** Nome do usuário do schema de teste. */
    String CHAVE_DB_TESTE_USER = "chave.db.teste.user";

    /** Senha do usuário do schema de teste. */
    String CHAVE_DB_TESTE_PASSWORD = "chave.db.teste.password";

    /** Mensagem de validação de campo duplicado. */
    String MSG_ERRO_VALIDACAO_DUPLICACAO_ENTIDADE = "msg.erro.campo.duplicado";

    /** Mensagem de validação de registro bloqueado para exclusao. */
    String MSG_ERRO_EXCLUSAO_BLOQUEADA = "msg.erro.exclusao.registro.bloqueado";

	String MSG_PROPRIEDADES_NAO_ENCONTRADA = "msg.erro.propriedades.nao.encontrada";
	
	String MSG_ERRO_DATA_NASCIMENT0_MENOR_DATA_ATUAL = "msg.erro.data.nascimento.menor.data.atual";
	
	String MSG_AVISO_VALIDACAO_EXCLUSAO_MULTIPLA_LISTA_VAZIA = "msg.aviso.validacao.exclusao.multipla.lista.vazia";
	
	String MSG_INFO_QUANTIDADE_REGISTROS_EXCLUIDOS = "msg.info.quantidade.registros.excluidos";
	
	String MSG_SIM = "label.sim";
	
	String MSG_NAO = "label.nao";
	
	Locale LOCALE_PT_BR = new Locale("pt", "BR");

	String USUARIO_SESSAO = "usuario";
	
	String SENHA_PADRAO = "123456";
	
	String REDIRECT_TO = "redirectTo";
}
