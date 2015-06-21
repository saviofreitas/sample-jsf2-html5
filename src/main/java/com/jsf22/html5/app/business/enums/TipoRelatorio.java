package com.jsf22.html5.app.business.enums;

/**
 * Enumeração com os formatos dos relatórios.
 * 
 */

public enum TipoRelatorio {
	CSV("csv"),
    DOCX("docx"),
	EXCEL("xls"),
	HTML("html"),
    ODS("ods"),
	PDF("pdf"),
    PDF_DOWNLOAD("pdf"),
    PLANILHA_OPEN_OFFICE("ods"),
    TEXT("txt"),
    XML("xml");

    private String extensao;

    /**
     * Construtor da classe.
     * 
     * @param extensao
     */
    private TipoRelatorio(String extensao) {

        this.extensao = extensao;
    }

    /**
     * Retorna o valor do atributo extensao.
     * 
     * @return Valor do atributo extensao.
     */
    public String getExtensao() {

        return extensao;
    }
}
