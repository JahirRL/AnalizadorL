import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class AnalizadorL {
	Scanner sc = new Scanner(System.in);
	
	static String matrizAsociada[][] = new  String[100][100];
	static ArrayList<Character> letra = new ArrayList<Character>();
	static ArrayList<Character> token = new ArrayList<Character>();
	static ArrayList<Character> digito = new ArrayList<Character>();
	static ArrayList<Character> archivo= new ArrayList<Character>();
	static ArrayList<String> palabrasReservadas = new ArrayList<String>();
	
	public static void asignarDatos() {
		
		letra.add('a');
		letra.add('b');
		letra.add('c');
		letra.add('d');
		letra.add('e');
		letra.add('f');
		letra.add('g');
		letra.add('h');
		letra.add('i');
		letra.add('j');
		letra.add('k');
		letra.add('l');
		letra.add('m');
		letra.add('n');
		letra.add('ñ');
		letra.add('o');
		letra.add('p');
		letra.add('q');
		letra.add('r');
		letra.add('s');
		letra.add('t');
		letra.add('u');
		letra.add('v');
		letra.add('w');
		letra.add('x');
		letra.add('y');
		letra.add('z');
		
		digito.add('0');
		digito.add('1');
		digito.add('2');
		digito.add('3');
		digito.add('4');
		digito.add('5');
		digito.add('6');
		digito.add('7');
		digito.add('8');
		digito.add('9');
		
        palabrasReservadas.add("programa");
        palabrasReservadas.add("finprograma");
        palabrasReservadas.add("si");
        palabrasReservadas.add("sino");
        palabrasReservadas.add("finsi");
        palabrasReservadas.add("segunsea");
        palabrasReservadas.add("caso");
        palabrasReservadas.add("fincaso");
        palabrasReservadas.add("leer");
		palabrasReservadas.add("imprimir");
		palabrasReservadas.add("inicio");
        palabrasReservadas.add("fin");
		palabrasReservadas.add("proceso");
        palabrasReservadas.add("finproceso");
        palabrasReservadas.add("salir");
        palabrasReservadas.add("real");
        palabrasReservadas.add("nada");
        palabrasReservadas.add("mientras");
        palabrasReservadas.add("finmientras");
        palabrasReservadas.add("limpiarpantalla");
        
		
        llenarMatriz();
        
        System.out.println("\n");
		
		System.out.print("letra= {");
		for(int i=0; i<letra.size()-2;i++) {
			System.out.print(letra.get(i)+", ");
		}
		System.out.println(letra.get(letra.size()-1)+"}");
		
		System.out.print("digito= {");
		for(int i=0; i<digito.size()-2;i++) {
			System.out.print(digito.get(i)+", ");
		}
		System.out.println(digito.get(digito.size()-1)+"}");
		
		System.out.println("\n");
				
		for(int i=0;i<=28;i++) {
			for(int j=0;j<=15;j++) {
				if(i==0) {
					if(j>=2) {
						System.out.print(matrizAsociada[i][j]+" \t");
					}
					else {
						System.out.print(matrizAsociada[i][j]+"    ");
					}
				}
				else {
					if(matrizAsociada[i][j]=="-1")
						System.out.print("Error\t");
					else
						System.out.print(matrizAsociada[i][j]+"\t");
				}
			}
			System.out.println("");
		}
		
		System.out.println("\n");
	}
	
    public static void llenarMatriz(){
        
        //-------------Encabezado y estados----------------//
        matrizAsociada[0][0]="   ";
        matrizAsociada[0][1]="letra"; //(1) es letra
        matrizAsociada[0][2]="digito"; //(2) es dígito
        matrizAsociada[0][3]="_"; //(3) es guíon bajo
        matrizAsociada[0][4]="."; //(4) es punto
        matrizAsociada[0][5]=Character.toString('"'); //(5) es comillas
        matrizAsociada[0][6]="<"; //(6) es menor que
        matrizAsociada[0][7]=">"; //(7) es mayor que
        matrizAsociada[0][8]="+"; //(8) es más
        matrizAsociada[0][9]="-"; //(9) es guíon
        matrizAsociada[0][10]="/"; //(10) es diagonal
        matrizAsociada[0][11]="*"; //(11) es asterisco
        matrizAsociada[0][12]="="; //(12) es igual
        matrizAsociada[0][13]="^"; //(13) es potencia
        matrizAsociada[0][14]="otra cosa"; //(14) cualquier otra cosa
        matrizAsociada[0][15]="";
        
        for(int j=1;j<=28;j++) {
            matrizAsociada[j][0]=String.valueOf(j);
        }
        //-------------------------------------------------//
        
        
        //-----------------------Estado 1-------------------//
        matrizAsociada[1][1]="2"; //(1) si recibo letra
        matrizAsociada[1][2]="6"; //(2) si recibo dígito
        matrizAsociada[1][3]="-1"; //(3) si recibo guíon bajo
        matrizAsociada[1][4]="-1"; //(4) si recibo punto
        matrizAsociada[1][5]="11"; //(5) si recibo comillas
        matrizAsociada[1][6]="13"; //(6) si recibo menor que
        matrizAsociada[1][7]="17"; //(7) si recibo mayor que
        matrizAsociada[1][8]="18"; //(8) si recibo más
        matrizAsociada[1][9]="19"; //(9) si recibo guíon
        matrizAsociada[1][10]="21"; //(10) si recibo diagonal
        matrizAsociada[1][11]="26"; //(11) si recibo asterisco
        matrizAsociada[1][12]="27"; //(12) si recibo igual
        matrizAsociada[1][13]="28"; //(13) si recibo potencia
        matrizAsociada[1][14]="-1"; //(14) si recibo otra cosa
        matrizAsociada[1][15]="";
        //-------------------------------------------------//
        
        
        //-----------------------Estado 2-------------------//
        matrizAsociada[2][1]="2"; //(1) si recibo letra
        matrizAsociada[2][2]="2"; //(2) si recibo dígito
        matrizAsociada[2][3]="3"; //(3) si recibo guíon bajo
        for(int j=4;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[2][j]="5";
        }
        matrizAsociada[2][15]="";
        //-------------------------------------------------//
        
        //-----------------------Estado 3-------------------//
        matrizAsociada[3][1]="4"; //(1) si recibo letra
        matrizAsociada[3][2]="4"; //(2) si recibo dígito
        for(int j=3;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[3][j]="-1";
        }
        matrizAsociada[3][15]="";
        //-------------------------------------------------//
        
        //-----------------------Estado 4-------------------//
        matrizAsociada[4][1]="2"; //(1) si recibo letra
        matrizAsociada[4][2]="2"; //(2) si recibo dígito
        matrizAsociada[4][3]="3"; //(3) si recibo guíon bajo
        for(int j=4;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[4][j]="5";
        }
        matrizAsociada[4][15]="";
        //-------------------------------------------------//
        
        //---------------Estado 5 Aceptación---------------//
        for(int j=1;j<=14;j++) {
            matrizAsociada[5][j]="-";
        }
        matrizAsociada[5][15]="Identificador/Palabra reservada";
        //-------------------------------------------------//
        
        //-----------------------Estado 6-------------------//
        matrizAsociada[6][1]="7"; //(1) si recibo letra
        matrizAsociada[6][2]="6"; //(2) si recibo dígito
        matrizAsociada[6][3]="7"; //(3) si recibo guíon bajo
        matrizAsociada[6][4]="8"; //(4) si recibo punto
        for(int j=5;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[6][j]="7";
        }
        matrizAsociada[6][15]="";
        //-------------------------------------------------//
        
        //---------------Estado 7 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[7][i]="-";
        }
        matrizAsociada[7][15]="Entero/Integer";
        //-------------------------------------------------//
        
        //-----------------------Estado 8-------------------//
        matrizAsociada[8][1]="-1"; //(1) si recibo letra
        matrizAsociada[8][2]="9"; //(2) si recibo dígito
        for(int j=3;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[8][j]="-1";
        }
        matrizAsociada[8][15]="";
        //-------------------------------------------------//
        
        //---------------------Estado 9-------------------//
        matrizAsociada[9][1]="10"; //(1) si recibo letra
        matrizAsociada[9][2]="9"; //(2) si recibo dígito
        for(int j=3;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[9][j]="10";
        }
        matrizAsociada[9][15]="";
        //-------------------------------------------------//
        
        //--------------Estado 10 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[10][i]="-";
        }
        matrizAsociada[10][15]="Flotante/Real";
        //-------------------------------------------------//
        
        //----------------------Estado 11------------------//
        for(int j=1;j<=4;j++) { //si recibo otra cosa
        	matrizAsociada[11][j]="11";
        }
        matrizAsociada[11][5]="12"; //(5) si recibo comillas
        for(int j=6;j<=14;j++) { //si recibo otra cosa
        	matrizAsociada[11][j]="11";
        }
        matrizAsociada[11][15]="";
        //-------------------------------------------------//
        
        //--------------Estado 12 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[12][i]="-";
        }
        matrizAsociada[12][15]="Cadena/String";
        //-------------------------------------------------//
        
        //----------------------Estado 13------------------//
        for(int j=1;j<=8;j++) { //si recibo otra cosa
            matrizAsociada[13][j]="14";
        }
        matrizAsociada[13][9]="15";
        for(int j=10;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[13][j]="14";
        }
        matrizAsociada[13][15]="";
        //-------------------------------------------------//
        
        //--------------Estado 14 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[14][i]="-";
        }
        matrizAsociada[14][15]="Operador <";
        //-------------------------------------------------//
        
        //----------------------Estado 15------------------//
        for(int j=1;j<=8;j++) { //si recibo otra cosa
            matrizAsociada[15][j]="-1";
        }
        matrizAsociada[15][9]="16";
        for(int j=10;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[15][j]="-1";
        }
        matrizAsociada[15][15]="";
        //-------------------------------------------------//
        
        //--------------Estado 16 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[16][i]="-";
        }
        matrizAsociada[16][15]="Asignacíon";
        //-------------------------------------------------//
        
        //--------------Estado 17 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[17][i]="-";
        }
        matrizAsociada[17][15]="Operador >";
        //-------------------------------------------------//
        
        //--------------Estado 18 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[18][i]="-";
        }
        matrizAsociada[18][15]="Operador +";
        //-------------------------------------------------//
        
        //----------------------Estado 19------------------//
        matrizAsociada[19][1]="20";
        matrizAsociada[19][2]="6";
        for(int j=3;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[19][j]="20";
        }
        matrizAsociada[19][15]="";
        //-------------------------------------------------//
        
        //--------------Estado 20 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[20][i]="-";
        }
        matrizAsociada[20][15]="Operador -";
        //-------------------------------------------------//
        
        //----------------------Estado 21------------------//
        for(int j=1;j<=10;j++) { //si recibo otra cosa
            matrizAsociada[21][j]="25";
        }
        matrizAsociada[21][11]="22";
        for(int j=12;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[21][j]="25";
        }
        matrizAsociada[21][15]="";
        //-------------------------------------------------//
        
        //----------------------Estado 22------------------//
        for(int j=1;j<=10;j++) { //si recibo otra cosa
            matrizAsociada[22][j]="22";
        }
        matrizAsociada[22][11]="23";
        for(int j=12;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[22][j]="22";
        }
        matrizAsociada[22][15]="";
        //-------------------------------------------------//
        
        //----------------------Estado 23------------------//
        for(int j=1;j<=9;j++) { //si recibo otra cosa
            matrizAsociada[23][j]="22";
        }
        matrizAsociada[23][10]="24";
        for(int j=11;j<=14;j++) { //si recibo otra cosa
            matrizAsociada[23][j]="22";
        }
        matrizAsociada[23][15]="";
        //-------------------------------------------------//
        
        //--------------Estado 24 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[24][i]="-";
        }
        matrizAsociada[24][15]="Comentarios";
        //-------------------------------------------------//
        
        //--------------Estado 25 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[25][i]="-";
        }
        matrizAsociada[25][15]="Operador /";
        //-------------------------------------------------//
        
        //--------------Estado 26 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[26][i]="-";
        }
        matrizAsociada[26][15]="Operador *";
        //-------------------------------------------------//
        
        //--------------Estado 27 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[27][i]="-";
        }
        matrizAsociada[27][15]="Operador =";
        //-------------------------------------------------//
        
        //--------------Estado 28 Aceptación---------------//
        for(int i=1;i<=14;i++) {
            matrizAsociada[28][i]="-";
        }
        matrizAsociada[28][15]="Operador ^";
        //-------------------------------------------------//
        
    }
    
    public static void mostrarToken(int id) {
    	
    	StringBuilder result = new StringBuilder(token.size());
        for (Character c : token) {
            result.append(c);
        }
        String tokenF = result.toString();
    	
        System.out.print("\n\nSe ha generado un token de tipo ");
        
        if(id==5) {
        	if(palabrasReservadas.contains(tokenF)) {
        		System.out.println("Palabra reservada: "+tokenF+"\n");
        	}
        	else {
        		System.out.println("Identificador: "+tokenF+"\n");
        	}
        }
        else {
        	System.out.println(matrizAsociada[id][15]+": "+tokenF+"\n");
        }
        
        token.clear();
        
    }
    
    public static void recuperarArchivo(String rutaArchivo) {
    	
    	int caracter=0;
        FileReader lectorArchivo = null;
        
        
    	try {
			
    		lectorArchivo = new FileReader(rutaArchivo);
			
			while(caracter != -1) {
				
				if(caracter!=-1) {
					caracter = lectorArchivo.read();
					if(caracter==-1){
						archivo.add('$');
					}else {
						archivo.add((char)caracter);
					}
				}
			}
			}catch (FileNotFoundException e) {
				System.out.println("Error: Fichero no encontrado");
				System.out.println(e.getMessage());
			}catch (Exception e) {
				System.out.println("Error de lectura del fichero");
				System.out.println(e.getMessage());
				}finally {
					try {
						if(lectorArchivo != null)
							lectorArchivo.close();
						}catch (Exception e) {
							System.out.println("Error al cerrar el fichero");
							System.out.println(e.getMessage());
							}
					}
    }
    
	public static void main(String args[]) {
		
		String rutaArchivo="ejemplo.txt";
		char item,itemE='€';
		int estado=1;
		int contadorLineas=1;
        int entrada=0;
        
        asignarDatos();
        recuperarArchivo(rutaArchivo);
        
		System.out.println("Estado actual\tEntrada\t\tNuevo estado");
		
		for(int contador=0;contador<archivo.size();contador++) {
			
			item=archivo.get(contador);
			item=Character.toLowerCase(item);
			
			System.out.print(estado+"\t\t");
			System.out.print(item+"\t\t");
            
            if((letra.contains(item))||(digito.contains(item))){
                
                if(letra.contains(item)){
                    itemE='~';
                }
                if(digito.contains(item)){
                    itemE='¥';
                }
            }
            else {
            	itemE=item;
            }
			
			if(item=='\n')
				contadorLineas++;
			
			if(item=='\t')
				itemE=' ';
            
            
            switch(itemE){
                case '~':
                    entrada=1;
                    break;
                    
                case '¥':
                    entrada=2;
                    break;
                    
                case '_':
                    entrada=3;
                    break;
                    
                case '.':
                    entrada=4;
                    break;
                
                case '"':
                    entrada=5;
                    break;
                    
                case '<':
                    entrada=6;
                    break;
                    
                case '>':
                    entrada=7;
                    break;
                    
                case '+':
                    entrada=8;
                    break;
                    
                case '-':
                    entrada=9;
                    break;
                    
                case '/':
                    entrada=10;
                    break;
                    
                case '*':
                    entrada=11;
                    break;
                    
                case '=':
                    entrada=12;
                    break;
                    
                case '^':
                    entrada=13;
                    break;
                    
                default:
                    entrada=14;
                    break;
            }
            
			
			//------------------------Cambio de estado---------------------//
            estado=Integer.parseInt(matrizAsociada[estado][entrada]);
            
            if(estado!=-1) {
            	
            	if((estado==12)||(estado==16)||(estado==17)||(estado==24)||(estado==27)||(estado==28)) {
            		token.add(item);
            		mostrarToken(estado);
    				estado=1;
    				contador+=1;
            	}
                
            	if((estado==5)||(estado==7)||(estado==10)||(estado==14)||(estado==25)||(estado==26)) {
            		mostrarToken(estado);
    				estado=1;
            	}
            	else {
            		token.add(item);
            	}
            }
            else {
            	
            	if(item=='$') {
            		System.out.println("\n\n¡Ha llegado al final del archivo!");
            		System.exit(0);
            	}
            	else {
            		System.out.println("Error en la linea: "+contadorLineas+"\n\nFin de la ejecución");
                    System.exit(0);
            	}
            }
            
          //------------------------------------------------------//
            
            System.out.println(estado);
			
		}
		
	}
}
