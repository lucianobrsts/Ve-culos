package br.com.veiculos.model.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import br.com.veiculos.model.Cep;

public class CepAdapter extends XmlAdapter<String, Cep> {

	@Override
	public String marshal(Cep value) throws Exception {
		if (value == null)
			return "00000-000";

		return value.getRegiao() + "-" + value.getSufixo();
	}

	@Override
	public Cep unmarshal(String value) throws Exception {

		if (value == null) {
			Cep cep = new Cep();
			cep.setRegiao("00000");
			cep.setSufixo("000");

			return cep;
		}

		if (value.length() == 8) {
			if (Double.parseDouble(value) > 0) {

				Cep cep = new Cep();
				cep.setRegiao(value.substring(0, 5));
				cep.setSufixo(value.substring(5));

				return cep;
			}

			return new Cep();
		} else
			throw new Exception();

	}

}
