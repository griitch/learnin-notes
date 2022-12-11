package misc;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] arr;
	private int capacity;
	private int size = 0;
	
	private void resize() {
		capacity *= 2;
		Item[] temp = arr;
		arr = (Item[]) new Object[capacity];
		
		for(int i = 0 ; i < temp.length ; i++)
			arr[i] = temp[i];
		
		for(int i = temp.length ; i < capacity ; i++ )
			arr[i] = null;
	}
	
	private class randomQiterator implements Iterator<Item> {
		private Item[] items;
		private int pointer = 0;
		
		public randomQiterator() {
			items = (Item[]) new Object[size];
			int i = 0;
			for(int j = 0 ; j < capacity ; j++)
			{
				if(arr[j] != null)
				{
					items[i] = arr[j];
					i++;
				}
			}
			StdRandom.shuffle(items);
		}
		
		public boolean hasNext() {
			return (pointer < size);
		}

		public Item next() {
			
			if(pointer == size)
					throw new NoSuchElementException();
			return items[pointer++];
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	
	}
	
	private int getRandomIndex() {
		
		return StdRandom.uniform(capacity);
	}	
	
	public RandomizedQueue() {
		arr = (Item[]) new Object[8];
		capacity = 8;
		for(Item e : arr)
			e = null;
		
	}
		
		
	public boolean isEmpty() { return size == 0 ; }
	
	public int size() { return size ; }
	
	public void enqueue(Item item)
	{
		if(item == null)
			throw new IllegalArgumentException();
		
		int index = getRandomIndex();
		
		while(arr[index] != null ) {
			index = getRandomIndex();
		}
		arr[index] = item;
		size++;
		if(size == capacity)
			resize();

	}

	
	public Item dequeue()
	{
		if(size == 0)
			throw new NoSuchElementException();
		
		int index = getRandomIndex();
		while(arr[index] == null ) {
			index = getRandomIndex();
		}
		Item value = arr[index];
		arr[index] = null;
		size--;
		return value;	
	}
	
	public Item sample()
	{
		if(size == 0)
			throw new NoSuchElementException();
		Item rand = arr[getRandomIndex()];	
		while(rand == null)
			rand = arr[getRandomIndex()];	
		return rand;
			
	}
	
	public Iterator<Item> iterator() {
			return new randomQiterator();
	}
	
	
	
	public static void main(String[] args) {
		
		RandomizedQueue<Integer> q = new RandomizedQueue<>();
		System.out.println(q.isEmpty());
		for(int i = 0 ; i < 7 ; i++)
			q.enqueue(i);
		q.dequeue();
		
		System.out.println(q.sample().intValue());
		
		for(Integer e : q)
			System.out.println(e.intValue());
		
		
		
		
		
	}
	

}
