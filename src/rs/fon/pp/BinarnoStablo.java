package rs.fon.pp;

import java.util.LinkedList;
import java.util.Random;

public class BinarnoStablo {
	
	class Cvor{
		public int key;
		public String value;
		public Cvor left,right;
		Cvor(String v){
			int i=0;
			Cvor novi = new Cvor(v);
			novi.key=generisiBroj(i);
		}
	}
	
	public Cvor root;
	
	public void add(String podatak){
		if(podatak==null)
			return;
		Cvor novi = new Cvor(podatak);
		if(root==null){
			root=novi;
			return;
		}
		addToBST(novi,root);
		uredi(novi);
	}
	
	private void addToBST(Cvor novi,Cvor root){
		// za String sortirano
		if(root.value.compareToIgnoreCase(novi.value)>0){
			if(root.left==null){
				root.left=novi;
				return;
			}
			addToBST(novi, root.left);
		}
		if(root.value.compareToIgnoreCase(novi.value)<0){
			if(root.right==null){
				root.right=novi;
				return;
			}
			addToBST(novi, root.right);
		}
		if(root.value.compareToIgnoreCase(novi.value)==0){
			if(root.left==null){
				root.left=novi;
				return;
			}
			if(root.right==null){
				root.right=novi;
				return;
			}
			addToBST(novi, root.left);
		}	
		
	}
	public void uredi(Cvor neki){
	/**	if(visina(nadjiRoditelja(root,neki).left)-visina(nadjiRoditelja(root,neki).right)==2){
			Cvor a=nadjiRoditelja(root, neki);
			Cvor b=nadjiRoditelja(a, neki);
			
			if(visina(neki.left)-visina(neki.right)==1){
				Cvor c=a.right;
				a.right=b;
				b.left=c;
				if(root==b){
					root=a;
			}
			if(visina(neki.left)-visina(neki.right)==-1){
				Cvor d=a.right.left;
				b.left=a.right;
				a.right=d;
				b.left.left=a;
				b.left.right=b;
				
				
			}*/	
		Cvor pointer=neki;
		while(pointer!=root){
			if(visina(nadjiRoditelja(root,pointer).left)-visina(nadjiRoditelja(root,pointer).right)==2){
				Cvor a=nadjiRoditelja(root, pointer);
				if(visina(pointer.left)-visina(pointer.right)==-1){
					a.left=pointer.right;
					pointer.right=null;
					a.left.left=pointer;
				}
				Cvor b=nadjiRoditelja(root, a);
				if(b!=null){
					if(b.left==a){
						b.left=a.left;
						pointer.right=a;
						a.left=null;
					}
					if(b.right==a){
						b.right=a.left;
						pointer.right=a;
						a.left=null;
					}	
				}
				if(b==null){
					a.left=pointer.right;
					pointer.right=a;
					root=pointer;
				}
				return;
			}
			
			
			if(visina(nadjiRoditelja(root,pointer).left)-visina(nadjiRoditelja(root,pointer).right)==-2){
				Cvor a=nadjiRoditelja(root, pointer);
				if(visina(pointer.left)-visina(pointer.right)==1){
					a.right=pointer.left;
					pointer.left=null;
					a.right.right=pointer;
				}
				Cvor b=nadjiRoditelja(root, a);
				if(b!=null){
					if(b.left==a){
						b.left=a.right;
						pointer.left=a;
						a.right=null;
					}
					if(b.right==a){
						b.right=a.right;
						pointer.left=a;
						a.right=null;
					}	
				}
				if(b==null){
					a.right=pointer.left;
					pointer.left=a;
					root=pointer;
				}
				return;
			}
			
			if(nadjiRoditelja(root, pointer)==root)
				return;
			pointer=nadjiRoditelja(root, pointer);
		}
		
			
	}
	public Cvor nadjiRoditelja(Cvor pointerR,Cvor neki){
		if(pointerR==null || neki==pointerR)
			return null;
		if((pointerR.left==neki || pointerR.right==neki)){
			return pointerR;
		}
		if(nadjiRoditelja(pointerR.right, neki)==null)
			return nadjiRoditelja(pointerR.left, neki);
		return nadjiRoditelja(pointerR.right, neki);
	}
	/**
	 * Vraca visinu stabla
	 * @param cvor
	 * @return
	 */
	public int visina(Cvor cvor) {
		if (cvor == null)
		return 0;
		return 1 + Math.max(visina(cvor.left), visina(cvor.right));
		}
	
	/**
	 * atribut u kojem se nalaze svi iskorisceni kljucevi;
	 */
	LinkedList<Integer> usedNumbers = new LinkedList<Integer>();
	/**
	 * Metoda za generisanje kljuca
	 * @param i
	 * @return
	 */
	public int generisiBroj(int i){
		if(!(usedNumbers.contains(i))){
			usedNumbers.add(i);
			return i;
		}
		return generisiBroj(++i);
	}
	/**
	 * Metoda za pretrazivanje stable na osnovu kljuca,koristi pomocnu metodu istoimenu koja ima 2 parametra
	 * @param k
	 * @return
	 */
	public String getValue(int k){
		if(!(usedNumbers.contains(k)))
			return null; // a mozemo i staviti "Ne postoji takav cvor"
		return getValue(k, root);
	}
	public String getValue(int k,Cvor root){
		if(root==null)
			return null;
		if(root.key==k)
			return root.value;
		if(getValue(k, root.left)==null)
			return getValue(k, root.right);
		return getValue(k, root.left);
	}
	
	//metodu za pretvaranje u AVL
	
	
}
