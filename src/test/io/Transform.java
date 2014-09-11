package test.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transform {
	public static Object byteToObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);

			obj = oi.readObject();

			bi.close();
			oi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static byte[] objectToByte(Object obj) {
		byte[] bytes = null;
		try {
			// object to bytearray
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			bytes = bo.toByteArray();

			bo.close();
			oo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;

	}

	public static void main(String[] args)  {
		List<Vo> voList = new ArrayList<Vo>();
		for (int i = 0; i < 1000000; i++) {
			Vo vo = new Vo();
			vo.setId(i);
			vo.setName("this is a name!!!!!!!!");
			vo.setScore(1.9F + i);
			voList.add(vo);
		}
		Date startDate = new Date();
		byte[] byteArr =  objectToByte(voList);
		Date endDate = new Date();
		byteToObject(byteArr);
		Date endDate2 = new Date();
		System.out.println(byteArr.length);
		System.out.println(startDate.getTime() - endDate.getTime());
		System.out.println(endDate2.getTime() - endDate.getTime());
		MappedByteBuffer.wrap(byteArr);
 	}

}

class Vo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private float score;
	private String name2="dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd";
	private String name3="44444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444";
	private String name4="ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
