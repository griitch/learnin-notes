package misc;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque <Item> implements Iterable<Item> {


	private class node {
		private Item value;
		private node next = null;
		private node prev = null;
		public node(Item i) {
			value = i;
		}
	}
	
	private node begin = null;
	private node end = null ;
	private int size = 0;
	
	public boolean isEmpty() { return size == 0 ; }
	
	public int size() { return size ; }
	
	public void addFirst(Item item) {
		if(item == null)
			throw new IllegalArgumentException();
		
		node newNode = new node(item);
		if(!isEmpty()) {			
			newNode.next = begin;
			begin.prev = newNode;
		}
		begin = newNode;
		size++;
		if(end == null)
			end = newNode;
	}
	
	public void addLast(Item item) {
		if(item == null)
			throw new IllegalArgumentException();
		
		node newNode = new node(item);
		
		if(!isEmpty()) {
			newNode.prev = end;
			end.next = newNode;
		}
		end = newNode;
		size++;
		if(begin == null)
			begin = newNode;
	}
	
	public Item removeLast() {
		if(isEmpty())
			throw new NoSuchElementException();
		
		Item item = end.value;
		end = end.prev;
		if(end != null)
		end.next = null;	
		size--;
		return item;
	}
	
	public Item removeFirst() {
		if(isEmpty())
			throw new NoSuchElementException();
		
		Item item = begin.value;
		begin = begin.next;
		if(begin != null)
			begin.prev = null;		
		size--;
		return item;
	}
	
	
	@Override
	public Iterator<Item> iterator() {
		return new dequeIterator();
	}
	
	private class dequeIterator implements Iterator<Item>{
		
		private node iterator = begin;
		
		@Override
		public boolean hasNext() {
			return iterator != null;
		}

		@Override
		public Item next() {
			if(iterator == null)
				throw new NoSuchElementException();
			
			Item item = iterator.value;
			iterator = iterator.next;
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

	public static void main(String[] args) {
		
		Deque<Integer> dick = new Deque<>();
		System.out.println(dick.isEmpty());
		dick.addFirst(0);
		dick.addLast(2);
		dick.addLast(3);
		dick.addLast(4);
		dick.addFirst(-1);
		dick.addFirst(-2);
		dick.removeFirst();
		dick.removeLast();
		
		
		for(Integer e : dick)
			System.out.println(e.intValue());
		
		
		}
	
	
}
