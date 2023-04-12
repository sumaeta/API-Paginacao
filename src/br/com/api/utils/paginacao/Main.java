package br.com.api.utils.paginacao;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<String> lista = new ArrayList<>();

		lista.add("0001");
		lista.add("0002");
		lista.add("0003");
		lista.add("0004");
		lista.add("0005");
		// System.out.println("===========");
		lista.add("0006");
		lista.add("0007");
		lista.add("0008");
		lista.add("0009");
		lista.add("0010");
		// System.out.println("===========");
		lista.add("0011");
		lista.add("0012");
		lista.add("0013");
		lista.add("0014");
		lista.add("0015");
		// System.out.println("===========");
		lista.add("0016");
		lista.add("0017");
		lista.add("0018");
		lista.add("0019");
		lista.add("0020");
		
		Page<String> page = new Page.Builder<String>()
		        .pageNumber(1)
		        .pageSize(10)
		        .content(lista)
		        .build();
	
	System.out.println(page);
		
	}
}
