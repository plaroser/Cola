package Models;

import java.util.Arrays;

public class Cola<T> {
	// ATRIBUTOS
	private T[] elementos;
	private int cursor;
	private boolean fifoLifo;

	/**
	 * Reserva memoria para un array de 0 T elemento. Por defecto la cola es de
	 * tipo fifo.
	 */
	@SuppressWarnings("unchecked")
	public Cola() {
		this.elementos = (T[]) new Object[0];
		this.cursor = 0;
		this.fifoLifo = true;
	}

	/**
	 * Reserva memoria para un array de 0 T elemento.
	 * 
	 * @param fifo
	 *            indica si la cola es lifo o fifo.
	 */
	public Cola(boolean fifo) {
		this();
		this.fifoLifo = fifo;
	}

	/**
	 * Redimensiona el array y le añade un objeto al final.
	 * 
	 * @param element
	 *            Objeto a añadir.
	 */
	public void push(T element) {
		this.elementos = Arrays.copyOf(this.elementos, size() + 1);
		this.elementos[size() - 1] = element;
	}

	/**
	 * Redimensiona la cola y añade un array de elementos al final.
	 * 
	 * @param element
	 *            el array a añadir.
	 */
	public void push(T[] element) {
		for (T valor : element)
			this.push(valor);
	}

	/**
	 * Elimina un objeto
	 * 
	 * @param element
	 *            objeto a eliminar.
	 * @return resultado de la operacion.
	 */
	public boolean remove(T element) {
		int i;
		boolean estado = false;
		for (i = 0; i < size(); i++)
			if (element.equals(this.elementos[i])) {
				if (!estado) {
					estado = remove(i);
				} else {
					remove(i);
				}
			}
		return estado;
	}

	/**
	 * Elimina un objeto y redimensiona la cola.
	 * 
	 * @param i
	 *            indice del objeto a eliminar
	 * @return si ha completado la operacion correctamente.
	 */
	public boolean remove(int i) {
		if (i < this.size() - 1 && i >= 0) {
			int aux = size() - i - 1;
			if (aux >= 0) {
				System.arraycopy(this.elementos, i + 1, elementos, i, aux);
				this.elementos = Arrays.copyOf(this.elementos, size() - 1);
			}
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Devuelve y elimina el siguiente objeto segun el metodo seleccionado.
	 * 
	 * @return objeto a devolver.
	 */
	public T pop() {
		int indice;
		if (this.fifoLifo)
			indice = 0;
		else
			indice = size() - 1;
		T aux = this.elementos[indice];
		this.remove(indice);
		return aux;
	}
	/**
	 * Devuelve el objeto segun el indice indicado
	 * 
	 * @param indice
	 *            Indice indicado.
	 * @return Objeto correspondiente
	 */
	public T get(int indice) {
		if (indice < 0 || indice > size())
			throw new IndexOutOfBoundsException("Indice fuera de rango.");

		return elementos[indice];
	}

	/**
	 * Devuelve el ultimo objeto de la lista
	 * 
	 * @return el ultimo obleto de la lista.
	 */
	public T getLast() {
		if (!isEmpty())
			return get(size() - 1);
		else
			throw new IndexOutOfBoundsException("Cola vacia.");
	}

	/**
	 * Devuelve el primer objeto de la lista
	 * 
	 * @return Primer objeto de la lista.
	 */
	public T getFirst() {
		if (!isEmpty())
			return get(0);
		else
			throw new IndexOutOfBoundsException("Cola vacia.");
	}

	/**
	 * Elimina todos los elementos de la lista
	 * 
	 * @return Si esta vacia la lista.
	 */
	public boolean clear() {
		do {
			remove(0);
		} while (!isEmpty());
		return isEmpty();
	}

	/**
	 * Comprueba si hay un objeto dentro de la lista.
	 * 
	 * @param o
	 *            objeto a buscar dentro de la lista.
	 * @return resultado de la busqueda.
	 */
	public boolean contains(T o) {
		for (int i = 0; i < size(); i++)
			if (o.equals(this.elementos[i]))
				return true;

		return false;
	}

	/**
	 * Comprueba que la lista este vacia.
	 * 
	 * @return Si esta vacia la lista.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Comprueba la longitud de la cola.
	 * 
	 * @return el numero de elementos que contiene la cola.
	 */
	public int size() {
		return this.elementos.length;
	}

	/**
	 * Devuelve una lista dentro del rango indicado
	 * 
	 * @param indiceInicial
	 *            Indice por el que empieza la lista
	 * @param indiceFinal
	 *            Indice en el que termina la lista.
	 * @return Lista generada.
	 */
	public Cola<T> subLista(int indiceInicial, int indiceFinal) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Cola<T> aux = new Cola();
		if (indiceInicial > indiceFinal)
			throw new IndexOutOfBoundsException("Indice de inicio mayor que indice final.");
		if (indiceFinal > this.size())
			throw new IndexOutOfBoundsException("Indice final fuera de rango.");
		for (int i = indiceInicial; i < indiceFinal; i++) {
			aux.push(this.elementos[i]);
		}
		return aux;
	}

	/**
	 * Comprueba que hay un objeto anterior segun la posicion del cursor.
	 * 
	 * @return el resultado de la comprobacion
	 */
	public boolean hayAnterior() {
		return this.cursor != 0;
	}

	/**
	 * Compruaba que hay un objeto siguiente segun la posicion del cursor.
	 * 
	 * @return Resultado de la comprobacion.
	 */
	public boolean haySiguiente() {
		return this.cursor != (this.size());
	}

	/**
	 * Devuelve el siguiente indice
	 * 
	 * @return Indice
	 */
	public int siguienteIndice() {
		return cursor++;

	}

	/**
	 * Devuelve el anterior indice
	 * 
	 * @return Indice
	 */
	public int anteriorIndice() {
		if (hayAnterior())
			return --cursor;
		else
			return -1;
	}

	/**
	 * Establece el cursor en la posicion deseada
	 * 
	 * @param nuevoCursor
	 *            Posicion a establecer.
	 * @return Resultado de la operacion.
	 */
	public boolean establecerCursor(int nuevoCursor) {
		if (nuevoCursor < 0 || nuevoCursor < this.size())
			return false;
		this.cursor = nuevoCursor;
		return true;
	}

	/**
	 * Cambia el metodo de funcionamienteo de la lista de FIFO a LIFO
	 * 
	 * @return Si el FIFO esta activado
	 */
	public boolean cambiarMetodo() {
		this.fifoLifo = !this.fifoLifo;
		return this.fifoLifo;
	}

	/**
	 * Convierte la lista a String
	 */
	@Override
	public String toString() {
		String aux = "";
		for (T valor : this.elementos) {
			aux += valor.toString() + "\n-----\n";
		}
		return aux;
	}

}
