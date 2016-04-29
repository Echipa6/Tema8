package Usefull;




import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class SFTP {

	/**
	 *
	 */
	public SFTP() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void uploadRaport() {
		String SFTPHOST = "fenrir.info.uaic.ro";
		int    SFTPPORT = 22;
		String SFTPUSER = "dorin.miron";
																									String SFTPPASS = "";
		
		
		String SFTPWORKINGDIR = "/fenrir/studs/dorin.miron/html/";

		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;

		try{
			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT);
			session.setPassword(SFTPPASS);
			
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			
			session.setConfig(config);
			session.connect();
			
			channel = session.openChannel("sftp");
			channel.connect();
			
			channelSftp = (ChannelSftp)channel;
			channelSftp.cd(SFTPWORKINGDIR);
			
			System.out.println(System.getProperty("user.dir"));
			
			File f = new File("report.html");
			//File d= new File("theme.css");
			channelSftp.put(new FileInputStream(f), f.getName());
			
		//	channelSftp.put(new FileInputStream(d), d.getName());
			
			channelSftp.disconnect();
			channel.disconnect();
			session.disconnect();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
