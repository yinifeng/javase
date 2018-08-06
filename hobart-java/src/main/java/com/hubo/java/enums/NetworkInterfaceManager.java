package com.hubo.java.enums;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * 枚举单例获取ip
 * @author hobart
 *
 */
public enum NetworkInterfaceManager {
	INSTANCE;
	
	private InetAddress m_local;
	
	private InetAddress m_localHost;
	
	
	private NetworkInterfaceManager(){
		load();
	}
	
	public String getLocalHostAddress(){
		return m_local.getHostAddress();
	}
	
	public String getLocalHostName() {
		try {
			if (m_localHost == null) {
				m_localHost = InetAddress.getLocalHost();
			}
			return m_localHost.getHostName();
		} catch (UnknownHostException e) {
			return m_local.getHostName();
		}
	}


	private void load() {
		String ip = getProperty("host.ip");
		
		if (ip != null) {
			try {
				m_local=InetAddress.getByName(ip);
				return;
			} catch (UnknownHostException e) {
				System.err.println(e);
			}
		}
		
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			List<NetworkInterface> nis= interfaces == null ? Collections.<NetworkInterface>emptyList() : Collections.<NetworkInterface>list(interfaces);
			List<InetAddress> addresses = new ArrayList<>();
			InetAddress local=null;
			
			try {
				for (NetworkInterface ni : nis) {
					if (ni.isUp() && !ni.isLoopback()) {
						addresses.addAll(Collections.list(ni.getInetAddresses()));
					}
				} 
				System.out.println("addresses >>>>"+addresses.size());
				long start = System.currentTimeMillis();
				local = findValidateIp(addresses);
				System.out.println("初始化耗时："+(System.currentTimeMillis()-start));
			} catch (Exception e) {
			}
			
			if (local != null) {
				m_local = local;
				return;
			}
		} catch (SocketException e) {
		}
		m_local = InetAddress.getLoopbackAddress();
	}


	private InetAddress findValidateIp(List<InetAddress> addresses) {
		InetAddress local=null;
		int maxWeight= -1;
		for (InetAddress address : addresses) {
			if (address instanceof Inet4Address) {
				int weight= 0;
				if (address.isSiteLocalAddress()) {
					weight +=8;
				}
				if (address.isLinkLocalAddress()) {
					weight +=4;
				}
				if (address.isLoopbackAddress()) {
					weight +=2;
				}
				
				//has most name
				//TODO fix performance issue when calling getHostName
				if (!Objects.equals(address.getHostName(), address.getHostAddress())) {
					weight +=1;
				}
				
				if(weight > maxWeight){
					maxWeight= weight;
					local=address;
				}
			}
		}
		return local;
	}


	private String getProperty(String name) {
		String value=null;
		value = System.getProperty(name);
		if (value == null) {
			value = System.getenv(name);
		}
		return value;
	}
}
