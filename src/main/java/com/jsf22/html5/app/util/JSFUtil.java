package com.jsf22.html5.app.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class JSFUtil {

	/**
	 * Limpa os dados dos componentes de edição e de seus filhos,
	 * recursivamente. Checa se o componente é instância de EditableValueHolder
	 * e 'reseta' suas propriedades.
	 * <p>
	 * Quando este método, por algum motivo, não funcionar, parta para
	 * ignorância e limpe o componente assim:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * component.getChildren().clear()
	 * </pre>
	 * 
	 * </blockquote> :-)
	 */
	public static void cleanSubmittedValues(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			EditableValueHolder evh = (EditableValueHolder) component;
			evh.setSubmittedValue(null);
			evh.setValue(null);
			evh.setLocalValueSet(false);
			evh.setValid(true);
		}
		if (component.getChildCount() > 0) {
			for (UIComponent child : component.getChildren()) {
				cleanSubmittedValues(child);
			}
		}
	}

	public static void cleanSubmittedValues(String componentName) {
		UIComponent component = obterComponentePorId(componentName);
		if (component != null)
			cleanSubmittedValues(component);
	}

	public static void adicionaMensagemDeErro(String msg) {
		adicionaMensagem(FacesMessage.SEVERITY_ERROR, msg);
	}

	public static void adicionaMensagemDeSucesso(String msg) {
		adicionaMensagem(FacesMessage.SEVERITY_INFO, msg);
	}

	public static void adicionaMensagemDeInformacao(String msg) {
		adicionaMensagem(FacesMessage.SEVERITY_WARN, msg);
	}

	private static void adicionaMensagem(Severity severity, String msg) {
		FacesMessage fm = new FacesMessage(severity, msg, null);
		getFacesContext().addMessage("messages", fm);
	}
	
	/**
     * Retorna o {@link UIComponent} correspondente ao Id informado.
     * 
     * @param id Identificador do componente (<tt>Se o <b>prependId</b> não estiver definido para <i>false</i>, deve-se
     *        informar o ID do formulário junto com o ID do componente</tt>).
     * @return {@link UIComponent}.
     */
    public static UIComponent obterComponentePorId(String id) {
        return getFacesContext().getViewRoot().findComponent(id);
    }
    
    /**
     * Retorna o caminho do contexto da aplicação.
     * 
     * @return Caminho do contexto.
     */
    public static String getContextPath() {
        return getExternalContext().getRequestContextPath();
    }

    /**
     * Retorna o caminho absoluto de um recurso baseado em seu caminho relativo.
     * 
     * @param caminhoRelativo Caminho relativo do recurso.
     * @return Caminho absoluto do recurso.
     */
    public static String getRealPath(String caminhoRelativo) {
        return getExternalContext().getRealPath(caminhoRelativo);
    }

    /**
     * Retorna um par�metro armazenado no {@link Map} do Request.
     * 
     * @param chave Chave do par�metro.
     * @return Valor do par�metro.
     */
    public static String getParametroRequest(String chave) {
        return getRequestParameterMap().get(chave);
    }

    /**
     * Armazena um par�metro no {@link Map} do Request.
     * 
     * @param chave Chave do par�metro.
     * @param valor Valor do par�metro.
     */
    public static void setParametroRequest(String chave, String valor) {
        getRequestParameterMap().put(chave, valor);
    }

    /**
     * Retorna um par�metro armazenado no {@link Map} da {@link Flash}.
     * 
     * @param chave Chave do par�metro.
     * @return Valor do par�metro.
     */
    public static Object getParametroFlash(String chave) {
        return getFlash().get(chave);
    }

    /**
     * Armazena um par�metro no {@link Map} do {@link Flash}.
     * 
     * @param chave Chave do par�metro.
     * @param valor Valor do par�metro.
     */
    public static void setParametroFlash(String chave, Object valor) {
        getFlash().put(chave, valor);
    }

    /**
     * Retorna um par�metro armazenado no {@link Map} da View.
     * 
     * @param chave Chave do par�metro.
     * @return Valor do par�metro.
     */
    public static Object getParametroView(String chave) {
        return getViewMap().get(chave);
    }

    /**
     * Armazena um par�metro no {@link Map} da View.
     * 
     * @param chave Chave do par�metro.
     * @param valor Valor do par�metro.
     */
    public static void setParametroView(String chave, Object valor) {
        getViewMap().put(chave, valor);
    }

    /**
     * Retorna um par�metro armazenado no {@link Map} da Session.
     * 
     * @param chave Chave do par�metro.
     * @return Valor do par�metro.
     */
    public static Object getParametroSession(String chave) {
        return getSessionMap().get(chave);
    }

    /**
     * Armazena um par�metro no {@link Map} do Session.
     * 
     * @param chave Chave do par�metro.
     * @param valor Valor do par�metro.
     */
    public static void setParametroSession(String chave, Object valor) {
        getSessionMap().put(chave, valor);
    }

    public static void removerParametroSession(String chave) {
        getSessionMap().remove(chave);
    }

    public static Map<String, String> getRequestParameterMap() {
        return getExternalContext().getRequestParameterMap();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }
    
    public static HttpSession getSession() {
    	return (HttpSession) getExternalContext().getSession(false);
    }

    public static Flash getFlash() {
        return getExternalContext().getFlash();
    }

    public static Map<String, Object> getViewMap() {
        return getFacesContext().getViewRoot().getViewMap();
    }

    public static Map<String, Object> getSessionMap() {
        return getExternalContext().getSessionMap();
    }
    
    public static ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
    
    public <T> T avaliaEl(String el, Class<T> clazz) {
		return (T) getFacesContext().getApplication().evaluateExpressionGet(getFacesContext(), el, clazz); 
	}
    
    public static String getViewId() {
    	return getFacesContext().getViewRoot().getViewId();
    }
}
