import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Principal {
	
	public static void main(String args[]) {

		try {
		RandomAccessFile arq = new RandomAccessFile("cep_ordenado.dat", "r");
		Scanner sc = new Scanner(System.in);
		Endereco endereco = new Endereco();
		
		String cep, aux;
		long inicio = 0;
		long meio;
		long fim = (arq.length() / endereco.tamanhoLinha());
		boolean encontrado = false;
		
		System.out.print("Digite o CEP: ");
		cep = sc.nextLine();
		sc.close();

		//C�digo Busca Bin�ria

		while(inicio <= fim) {

			meio = (inicio + fim)/2;
			arq.seek(meio * endereco.tamanhoLinha());
			endereco.lerEndereco(arq);
			aux = endereco.getCEP();
			if(aux.compareTo(cep)!=0) {
			if(aux.compareTo(cep) < 0) {
				inicio = meio + 1;
			}
			else if(aux.compareTo(cep) > 0) {
				fim = meio - 1;
			}
			}else
				if(aux.compareTo(cep)==0) {
					encontrado = true;
					break;
				} 


			
			
		}
		if(encontrado == true){
			System.out.println(cep);
			System.out.println("CEP encontrado!");
			System.out.println("Endere�o: ");
			System.out.println("Logradouro: " + endereco.getLogradouro());
			System.out.println("Bairro: " + endereco.getBairro());
			System.out.println("Cidade: " + endereco.getCidade());
			System.out.println("Estado: " + endereco.getEstado());
			System.out.println("Sigla: " + endereco.getSigla());
			System.out.println("CEP: " + endereco.getCEP());
		}else
		System.out.println("CEP n�o encontrado");
		
		arq.close();

		} catch(IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}


		}
	}