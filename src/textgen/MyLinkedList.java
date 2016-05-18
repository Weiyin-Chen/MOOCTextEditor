package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
         size = 0;
		 head = new LLNode<E>(null);
		 tail = new LLNode<E>(null);
		 head.next = tail;
		 tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		LLNode<E> cwy = new LLNode<E>(element);
		cwy.next = tail;
		cwy.prev = cwy.next.prev;
		cwy.next.prev = cwy;
		cwy.prev.next = cwy;
		size+=1;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		if (index<0 || index>=size){
			throw new IndexOutOfBoundsException();
		}
			LLNode<E> aa = head;
			int i=0;
			while (i<=index){
				aa = aa.next;
				i++;
			}
			return aa.data;
		
		
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (index<0 || index>size){
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> cwy1 = new LLNode<E>(element);
		LLNode<E> aa1 = head;
		int i=0;
		while (i<=index){
			aa1 = aa1.next;
			i++;
		}
		cwy1.next = aa1;
		cwy1.prev = aa1.prev;
		cwy1.next.prev = cwy1;
		cwy1.prev.next = cwy1;
		size+=1;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index<0 || index>=size){
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> aa1 = head;
		int i=0;
		while (i<=index){
			aa1 = aa1.next;
			i++;
		}
		aa1.next.prev = aa1.prev;
		aa1.prev.next = aa1.next;
		aa1.prev = null;
		aa1.next = null;
		size-=1;
		return aa1.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (index<0 || index>=size){
			throw new IndexOutOfBoundsException();
		}
		if (element == null){
			throw new NullPointerException();
		}
		LLNode<E> cwy1 = new LLNode<E>(element);
		LLNode<E> aa1 = head;
		int i=0;
		while (i<=index){
			aa1 = aa1.next;
			i++;
		}
		aa1.next.prev = cwy1;
		aa1.prev.next = cwy1;
		aa1.prev = null;
		aa1.next = null;
		
		return aa1.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
