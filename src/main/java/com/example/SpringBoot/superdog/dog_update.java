/****************************************************************************
*
* Demo program for the SuperDog update process
*
*
* Copyright (C) 2014 SafeNet, Inc. All rights reserved.
*
*
* SuperDog DEMOMA key required
*
****************************************************************************/

package com.example.springboot.superdog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;


class dog_update
{

	public static final String vendorCode = new String(
		"wEG6h5XDB0hlT6IaAf3e3fDwgfSDkVfiAZdSt2nj/vD/nQYt38OzbaylYRq2owHsO8UVkmjfaQHQM9IfLBsOV/wAxU1tmX/jCUmDY2vfJ9Sv2Deml6097aoHfrMzrIl3n+v5GONVEbu0IIz3VMh83b8DF5SOUptAxrD2F7zwI9huhSRHHVAoc6ha1J92gMSbj/f1QJCcnK8Wrdzn+NEg0e+fWCu4sow8C2YZQo5oLm3ardshZfxXDlqSYg/PUoefvm9WZhqG8mjO9bmePWDYfhoYZp5M0AnE2AwZEJlbo4w3vS7PZTDuNJOx8rxkOSa6qKuHAMiQEjIY978Y1Vbloz//EUq0nIOVqueWmAtgPzjgiVn9sgeB8EoEomkPRkqX7mZt7O3ekCK3w9AIhTuT0k5VTAU9/HNmTubhrLlzeuzi/hyxsP6YEAfryWIBBycsay1u1QX7ZPCxhNIFbxdFI0dAztPEmxUNausjnRNN94NAmzTS26Yv/XKUR+gtW0Rh0Dh5pa06tnpsVzJfAo71IT8X01rVgq033Ponw7ueJogy/hRAfe6eHtzhEU8Xf+PqgQRplASKLZKe9djIQf/aEsC9L8RZ9clgZamGBqe8QbE48Yh04GuNGdToA1wFxDfmhiK7aM4bhVg/1MRMOsaObEshY7YHDLNfF0GLMHEL1Axu1ItT/e7n89Zr860SCWeWLmkfuZH+eRbTuDI6+v1J2yYWlLIngjrA5sbT0YgdWpekT8csPeGw70eC5nEjorT/xb26kmqQDmfLf6YWMDxTE5JjrY0v22JFYj2CxOIC9NZ5ZnYAjTNeSsWNTChfw2J0tUeXKaeOLRaCS8Lb8yrPBxSG3dxvVev2QEytP5o6hi3mJ+POvmXwC+ChrvofxccjwL9izkCxWxnj3auvy+tE+rMgPIJQEiXZlU8svyjBSG47z3y78Jj3oxo27uZ3CxDsyhnNNjiX86lGkXePqDkA5g==");

	public static void main(String argv[]) throws IOException
	{
		String infos, fname;
		int input = 0;
		int status;
		Dog curDog = new Dog(Dog.DOG_DEFAULT_FID);
		byte[] update_buffer;
		RandomAccessFile f = null, f1 = null;
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(reader);
		String ack_data;
		String updatedata;

		System.out.println("\nThis is a simple demo program for SuperDog update functions");
		System.out.println("Copyright (C) SafeNet, Inc. All rights reserved.\n");

		System.out.println("Please choose whether to retrieve SuperDog");
		System.out.println("(i) information or to  ");
		System.out.println("(u) update SuperDog");

		while ((input != 'i') && (input != 'u'))
			input = in.read();

		/*
		 * consume the stream
		 */
		fname = in.readLine();

		if (input == 'i')
		{
			/*******************************************************************
			 * curDog.getInfo DOG_UPDATEINFO generate update information on
			 * customer site
			 */
			String scope = new String("<dogscope />\n");
			infos = curDog.getInfo(scope, Dog.DOG_UPDATEINFO, vendorCode);
			status = curDog.getLastError();

			switch (status)
			{
				case DogStatus.DOG_STATUS_OK:
					System.out.println("\nUpdate information successfully retrieved from SuperDog");
					System.out.println("\nPlease enter C2V file name (or <enter> for stdout):");

					fname = in.readLine();

					if (fname.length() == 0) 
					{
						System.out.println(infos);
					} 
					else 
					{
						try 
						{
							f = new RandomAccessFile(fname, "rw");
						} 
						catch (IOException e) 
						{
							System.out.println("Error: " + e.getMessage());
							return;
						}
						f.write(infos.getBytes());
						System.out.println("SuperDog information stored into file "
										+ fname);
					}
					break;
				case DogStatus.DOG_TOO_MANY_KEYS:
					System.out.println("Too many SuperDog connected\n");
					break;
				case DogStatus.DOG_INV_FORMAT:
					System.out.println("Invalid XML info format\n");
					break;
				case DogStatus.DOG_INV_SCOPE:
					System.out.println("Invalid XML scope\n");
					break;
				case DogStatus.DOG_LOCAL_COMM_ERR:
					System.out.println("communication error between API and local SuperDog License Manager");
					break;
				case DogStatus.DOG_SCOPE_RESULTS_EMPTY:
					System.out.println("unable to locate a Feature matching the scope");
					break;
				default:
					System.out.println("dog_get_info failed with status " + status);
			} /* switch (status) */
		} 
		else if (input == 'u') 
		{
			/* input was 'u' for update */

			/*******************************************************************
			 * curDog.update update SuperDog
			 */

			/* read update information from file */
			System.out.println("Please enter name of available V2C file: ");
			/*
			 * V2C files are generated with help of SuperDog License Design Utility
			 * or LicGen API
			 */
			fname = in.readLine();

			if (fname != null)
			{
				try
				{
					f = new RandomAccessFile(fname, "r");
				}
				catch (IOException e)
				{
					System.out.println("Error: " + e.getMessage());
					return;
				}
				update_buffer = new byte[(int) f.length()];
				if (update_buffer != null)
				{
					f.read(update_buffer);
					updatedata = new String(update_buffer);
					ack_data = curDog.update(updatedata);
					status = curDog.getLastError();

					switch (status)
					{
						case DogStatus.DOG_STATUS_OK:
							System.out.println("SuperDog successfully updated");
							if (ack_data != null)
							{
								/* save acknowledge data in file */
								f1 = new RandomAccessFile("dog_ack.c2v", "rw");
								f1.write(ack_data.getBytes());
								System.out.println("acknowledge data written to file dog_ack.c2v\n");
							}
							break;
						case DogStatus.DOG_INV_HND:
							System.out.println("handle not active\n");
							break;
						case DogStatus.DOG_INV_FORMAT:
							System.out.println("unrecognized format\n");
							break;
						case DogStatus.DOG_INV_UPDATE_DATA:
							System.out.println("invalid update data\n");
							break;
						case DogStatus.DOG_INV_UPDATE_NOTSUPP:
							System.out.println("SuperDog does not support updates\n");
							break;
						case DogStatus.DOG_INV_UPDATE_CNTR:
							System.out.println("invalid update counter\n");
							break;
						case DogStatus.DOG_LOCAL_COMM_ERR:
							System.out.println("communication error between API and local SuperDog License Manager");
							break;
						case DogStatus.DOG_SCOPE_RESULTS_EMPTY:
							System.out.println("unable to locate a Feature matching the scope");
							break;
						default:
							System.out.println("dog_update failed with status "
									+ status);
					}
				}
			}
			else
			{
				System.out.println("could not open file\n");
			}
		} 
		return;
	}
}
