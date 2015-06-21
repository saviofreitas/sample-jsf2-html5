package com.jsf22.html5.app;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.jsf22.html5.app.util.SenhaUtil;

public class Main {

	public static void main(String[] args) {
		try {
			System.out.println(SenhaUtil.SHA1("senha"));
			System.out.println(SenhaUtil.SHA1("teste"));
			System.out.println(SenhaUtil.SHA1("teste1"));
			
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
