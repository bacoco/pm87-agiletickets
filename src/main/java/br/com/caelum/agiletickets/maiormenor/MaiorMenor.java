package br.com.caelum.agiletickets.maiormenor;

public class MaiorMenor {

	private int maior = Integer.MIN_VALUE;
	private int menor = Integer.MAX_VALUE;

	public void encontra(int[] nums) {
		for (int n : nums) {
			if (n < menor) {
				menor = n;
			}if (n > maior) {
				maior = n;
			}
		}
	}

	public int getMaior() {
		return maior;
	}

	public int getMenor() {
		return menor;
	}

}
