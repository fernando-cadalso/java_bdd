package br.com.alura.leilao.acceptance.steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class PropondoLanceSteps {

	private Usuario usuario;
	private Lance lance;
	private Leilao leilao;
	private ArrayList<Lance> lista;

	@Before
	public void setup() {
		this.lista = new ArrayList<Lance>();
		leilao = new Leilao("Tablet XUZ");
	}

	@Dado("um lance valido")

	public void dado_um_lance_valido() {
		usuario = new Usuario("Fernando");
		lance = new Lance(usuario, BigDecimal.TEN);
		leilao = new Leilao("Tablet XUZ");
	}

	@Quando("propoe o lance")
	public void quando_propoe_o_lance() {
		leilao.propoe(lance);
	}

	@Então("o lance eh aceito")
	public void entao_o_lance_eh_aceito() {
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
	}

	@Dado("um lance de {double} reais do usuario {string}")
	public void um_lance_de_reais_do_usuario_fulano(Double valor, String nomeUsuario) {
		Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
		this.lista.add(lance);
	}
	
	@Dado("uma sequencia de lances validos")
	public void uma_sequencia_de_lances_validos(DataTable dataTable) {
	    List<Map<String, String>> valores = dataTable.asMaps();
	    for (Map<String, String> map : valores) {
	    	String nome = map.get("nomeUsuario");
	    	String valor = map.get("x");
	    	lance = new Lance(new Usuario(nome), new BigDecimal(valor));
	    	lista.add(lance);
			
		}
	}

	@Quando("propoe varios lances ao leilao")
	public void propoe_varios_lances_ao_leilao() {
		/*
		 * Percorre a lista de lances e adiciona no leilão
		 */
		this.lista.forEach(lance -> leilao.propoe(lance));
	}

	@Então("os lances sao aceitos")
	public void os_lances_sao_aceitos() {
		Assert.assertEquals(this.lista.size(), leilao.getLances().size());
		Assert.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
		Assert.assertEquals(this.lista.get(1).getValor(), leilao.getLances().get(1).getValor());
	}

	@Dado("um lance invalido de {double} reais")
	public void um_lance_invalido_de_reais(Double x) {

		lance = new Lance(new BigDecimal(x));
	}

	@Então("o lance nao deve ser aceito")
	public void o_lance_nao_deve_ser_aceito() {
		Assert.assertEquals(0, leilao.getLances().size());
	}

	@Dado("dois lances")
	public void dois_lances(DataTable dataTable) {
		List<Map<String, String>> valores = dataTable.asMaps();
		for (Map<String, String> mapa : valores) {
			String valor = mapa.get("x");
			String nome = mapa.get("nomeUsuario");
			lance = new Lance(new Usuario(nome), new BigDecimal(valor));
			lista.add(lance);
		}
	}

	@Então("o segundo lance nao deve ser aceito")
	public void o_segundo_lance_nao_deve_ser_aceito() {
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
	}
}