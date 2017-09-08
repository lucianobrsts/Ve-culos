package br.com.veiculos.model.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PlacaAdapter extends XmlAdapter<String, String> {

	@Override
	public String marshal(String value) throws Exception {
		if (value == null || value.trim().equals("")) {
			return null;
		}
		return value.substring(0, 3) + "-" + value.substring(3);
	}

	@Override
	public String unmarshal(String value) throws Exception {
		  if (value == null) {
		         return "";
		      }
		  return value.replace('-', ' ');
	}

}
