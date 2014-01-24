package onekr.biz.domain.impl;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import onekr.biz.domain.dto.DomainDto;

import org.springframework.stereotype.Service;

@Service("InternicDomainSeacher")
public class InternicDomainSeacher implements DomainSeach {
	private final static int port =43;//在TCP服务端口43
	private final static String hostname="whois.internic.net";//WHOIS服务器是

	@Override
	public DomainDto search(String domain) {
		StringBuffer stringBuffer = new StringBuffer();
		Socket theSocket;
		DataInputStream theWhoisStream;
		PrintStream ps;
		
		DomainDto dto = new DomainDto();
		dto.setDomain(domain);
		
		try {
			// 在TCP服务端口43（十进制）连接SRI-NIC服务主机
			theSocket = new Socket(hostname, port, true);
			ps = new PrintStream(theSocket.getOutputStream());
			ps.println("whois " + domain);// 发送命令到服务端
			ps.println("\r\n");
			// 接受相应命令的返回信息
			theWhoisStream = new DataInputStream(theSocket.getInputStream());
			String str;
			while ((str = theWhoisStream.readLine()) != null) {
				stringBuffer.append(str + "<br/>");
			}

			// 关闭DataInputStream和PrintWriter
			theWhoisStream.close();
			ps.close();
			theSocket.close();
		} catch (IOException ex) {
			return dto;
		}
		String whoisInfo = stringBuffer.toString();
		if (whoisInfo.contains("No match for")) {
			dto.setAvailable(true);
			dto.setWhoisInfo(whoisInfo);
		} else {
			dto.setAvailable(false);
			dto.setWhoisInfo(whoisInfo);
		}
		return dto;
	}
	
}
