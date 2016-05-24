/**
 * @source: http://www.java2s.com/Tutorial/Java/0140__Collections/CircularBuffer.htm
 * */
package com.googlecode.ounit.codecomparison.controller;

public class CircularBuffer {
	private String data[];
	private int head;
	private int tail;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CircularBuffer [] = ");
		for (int i = 0; i < data.length; i++) {
			sb.append("i= " + i + ", data=" + data[i] + "; ");
		}
		return sb.toString();
	}

	public String[] getData() {
		return data;
	}

	public int getTail() {
		return tail;
	}

	public CircularBuffer(Integer number) {
		data = new String[number];
		head = 0;
		tail = 0;
	}

	public boolean store(String value) {
		if (!bufferFull()) {
			data[tail++] = value;
			if (tail == data.length) {
				tail = 0;
			}
			return true;
		} else {
			return false;
		}
	}

	public String read() {
		if (head != tail) {
			String value = data[head++];
			if (head == data.length) {
				head = 0;
			}
			return value;
		} else {
			return null;
		}
	}

	private boolean bufferFull() {
		if (tail + 1 == head) {
			return true;
		}
		if (tail == (data.length - 1) && head == 0) {
			return true;
		}
		return false;
	}
}
