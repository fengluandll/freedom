package mx.com.televisa.derechocorporativo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.*;

public class ExecSSH {

	public void setSsh(String pShellName, String pHost, String pUsername, String pPassword, String pNumDocApeEng,
			String pRepository, String pPahtPdf, String pNamePdf) {
		String lstCommand = "sh " + pShellName + "  " + pUsername + " " + pPassword + " " + pNumDocApeEng + " "
				+ pRepository + " " + pPahtPdf + " " + pNamePdf + "";

		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(pUsername, pHost, 22);
			UserInfo ui = new SSHUserInfo(pPassword, null);
			session.setUserInfo(ui);
			session.setPassword(pPassword);
			session.connect();
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(lstCommand);
			channel.setInputStream(null);

			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();

			channel.connect();

			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					if (in.available() > 0)
						continue;
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}

	}

	public void getFile(String pHost, String pUsername, String pPassword, String pRemoteFileName,
			String pLocalFileName) {
		FileOutputStream fos = null;
		String lstRemoteFile = pRemoteFileName, lstLocalFile = pLocalFileName, lstPrefix = null;
		try {
			if (new File(lstLocalFile).isDirectory()) {
				lstPrefix = lstLocalFile + File.separator;
			}
			JSch jsch = new JSch();
			Session session = jsch.getSession(pUsername, pHost, 22);
			UserInfo ui = new SSHUserInfo(pPassword, null);
			session.setUserInfo(ui);
			session.setPassword(pPassword);
			session.connect();

			// exec 'scp -f rfile' remotely
			String command = "scp -f " + lstRemoteFile;
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			// get I/O streams for remote scp
			OutputStream out = channel.getOutputStream();
			InputStream in = channel.getInputStream();

			channel.connect();

			byte[] buf = new byte[1024];

			// send '\0'
			buf[0] = 0;
			out.write(buf, 0, 1);
			out.flush();

			while (true) {
				int c = checkAck(in);
				if (c != 'C') {
					break;
				}

				// read '0644 '
				in.read(buf, 0, 5);

				long filesize = 0L;
				while (true) {
					if (in.read(buf, 0, 1) < 0) {
						// error
						break;
					}
					if (buf[0] == ' ')
						break;
					filesize = filesize * 10L + (long) (buf[0] - '0');
				}

				String file = null;
				for (int i = 0;; i++) {
					in.read(buf, i, 1);
					if (buf[i] == (byte) 0x0a) {
						file = new String(buf, 0, i);
						break;
					}
				}

				// System.out.println("filesize="+filesize+", file="+file);

				// send '\0'
				buf[0] = 0;
				out.write(buf, 0, 1);
				out.flush();

				// read a content of lfile
				fos = new FileOutputStream(lstPrefix == null ? lstLocalFile : lstPrefix + file);
				int foo;
				while (true) {
					if (buf.length < filesize)
						foo = buf.length;
					else
						foo = (int) filesize;
					foo = in.read(buf, 0, foo);
					if (foo < 0) {
						// error
						break;
					}
					fos.write(buf, 0, foo);
					filesize -= foo;
					if (filesize == 0L)
						break;
				}
				fos.close();
				fos = null;

				if (checkAck(in) != 0) {
					System.exit(0);
				}

				// send '\0'
				buf[0] = 0;
				out.write(buf, 0, 1);
				out.flush();
			}

			session.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	static int checkAck(InputStream in) throws IOException {
		int b = in.read();
		// b may be 0 for success,
		// 1 for error,
		// 2 for fatal error,
		// -1
		if (b == 0)
			return b;
		if (b == -1)
			return b;

		if (b == 1 || b == 2) {
			StringBuffer sb = new StringBuffer();
			int c;
			do {
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');
			if (b == 1) { // error
				System.out.print(sb.toString());
			}
			if (b == 2) { // fatal error
				System.out.print(sb.toString());
			}
		}
		return b;
	}
	
	public void doEraseSsh(String pHost, String pUsername, String pPassword,String pPahtPdf, String pNamePdf) {
		String lstCommand = "rm " + pPahtPdf + "pdf/" + pNamePdf + ".pdf";
		System.out.println("Comando a ejecutar: "+ lstCommand);
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(pUsername, pHost, 22);
			UserInfo ui = new SSHUserInfo(pPassword, null);
			session.setUserInfo(ui);
			session.setPassword(pPassword);
			session.connect();
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(lstCommand);
			channel.setInputStream(null);

			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();

			channel.connect();

			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					if (in.available() > 0)
						continue;
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}

	}

/*	public static void main(String args[]) {
		ExecSSH exec = new ExecSSH();
		//exec.setSsh("/tmp/dctm-reposit/documentum.sh", "10.7.5.35", "dmadmin", "dmadmin", "52,016", "tvsa_jur",	"/tmp/dctm-reposit/", "52_016");
		//exec.getFile("10.7.5.35", "dmadmin", "dmadmin", "/tmp/dctm-reposit/pdf/52_016.pdf", System.getProperty("user.dir")+"/52_016_Copy.pdf");
		exec.doEraseSsh("10.7.5.35", "dmadmin", "dmadmin", "/tmp/dctm-reposit/", "52_016");
		System.out.println("PahtLocal: "+System.getProperty("user.dir"));
	}
*/
}
