package br.com.caelum.agiletickets.maiormenor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MaiorMenorTest {

	private MaiorMenor alg;
	
	@Before
	public void setUp() {
		alg = new MaiorMenor();
	}

	@Test
	public void numerosEmQualquerOrdem() {
		alg.encontra(new int[] { 4, 15, 7, 8 });

		assertEquals(15, alg.getMaior());
		assertEquals(4, alg.getMenor());
	}

	@Test
	public void crescente() {
		alg.encontra(new int[] { 1, 2, 3, 4, 5 });

		assertEquals(5, alg.getMaior());
		assertEquals(1, alg.getMenor());
	}

	@Test
	public void decrescente() {
		alg.encontra(new int[] { 5, 4, 3, 2, 1 });

		assertEquals(5, alg.getMaior());
		assertEquals(1, alg.getMenor());
	}

	@Test
	public void unicoNumero() {
		alg.encontra(new int[] { 1 });

		assertEquals(1, alg.getMaior());
		assertEquals(1, alg.getMenor());
	}

}
