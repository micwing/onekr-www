package onekr.biz.domain.impl;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;

import onekr.biz.domain.dto.DomainDto;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service("InternicDomainSeacher")
public class InternicDomainSeacher implements DomainSeach {
	private final static int port =43;//在TCP服务端口43
	private final static String hostname="whois.internic.net";//WHOIS服务器是

	@Override
	public DomainDto search(String domain) {
		StringBuffer stringBuffer = new StringBuffer();
		Socket theSocket;
		
		DomainDto dto = new DomainDto();
		dto.setDomain(domain);
		
		try {
			// 在TCP服务端口43（十进制）连接SRI-NIC服务主机
			SocketAddress  remoteAddr = new InetSocketAddress(hostname, port);
			theSocket = new Socket();
			theSocket.connect(remoteAddr, 5000);
			
			IOUtils.write("whois " + domain + "\r\n", theSocket.getOutputStream());
			
			// 接受相应命令的返回信息
			List<String> strList = IOUtils.readLines(theSocket.getInputStream());
			for (String str : strList) {
				stringBuffer.append(str + "<br/>");
			}

			// 关闭DataInputStream和PrintWriter
			theSocket.close();
		} catch (Exception ex) {
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
