package io.github.keshuangjie.androidutil.exception;

import java.io.IOException;
import java.io.InputStream;

public interface InputStreamProcessor {
	
	public void process(InputStream input) throws IOException;

}
