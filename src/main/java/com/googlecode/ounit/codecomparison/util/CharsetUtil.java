/**
 * @author Georgios Migdos
 * @source https://gmigdos.wordpress.com/2010/04/08/java-how-to-auto-detect-a-files-encoding/
 * */
package com.googlecode.ounit.codecomparison.util;

import java.io.BufferedInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.springframework.web.multipart.MultipartFile;

public class CharsetUtil {

	public Charset detectCharset(MultipartFile f, Charset charset) {
		try {
			BufferedInputStream input = new BufferedInputStream(f.getInputStream());

			CharsetDecoder decoder = charset.newDecoder();
			decoder.reset();

			byte[] buffer = new byte[512];
			boolean identified = false;
			while ((input.read(buffer) != -1) && (!identified)) {
				identified = identify(buffer, decoder);
			}

			input.close();

			if (identified) {
				return charset;
			} else {
				return null;
			}

		} catch (Exception e) {
			return null;
		}
	}

	public boolean identify(byte[] bytes, CharsetDecoder decoder) {
		try {
			decoder.decode(ByteBuffer.wrap(bytes));
		} catch (CharacterCodingException e) {
			return false;
		}
		return true;
	}

	public Charset getCharSetInUse(MultipartFile file) {
		Charset charset = null;
		String[] charsetsToBeTested = { "UTF-8" };

		for (String charsetName : charsetsToBeTested) {
			charset = detectCharset(file, Charset.forName(charsetName));
			if (charset != null) {
				break;
			}
		}
		return charset;
	}
}
