package io.github.keshuangjie.androidutil.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件处理工具
 *
 * @author keshuangjie
 * @date 2014-12-2 13:43:25
 */
public class FileUtil {
	
	/**
	 *  获取文件后缀名
	 *
	 *  @param file
	 *  @return
	 */
	public static String getSuffix(File file) {
		final String path = file.getAbsolutePath();
		int lastDot = path.lastIndexOf('.');

		return (lastDot >= 0) ? path.substring(lastDot+1) : "";
	}

	/**
	 *  获取文件后缀名
	 *
	 *  @param filename
	 *  @return
	 */
	public static String getSuffix(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

    /**
     * 将对象列表写到文件中
     * 
     * @param filename
     * @param objs
     */
    public static void writeObjectsToFile(String filename, List<Object> objs) {
        File file = new File(filename);

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            objOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Object obj : objs) {
                objOutputStream.writeObject(obj);
            }
        } catch (IOException e) {
            Log.e(FileUtil.class.getSimpleName() + "writeObjectsToFile() error:" + e.getMessage());
        } finally {
            try {
                if (objOutputStream != null) {
                    objOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                Log.e(FileUtil.class.getSimpleName() + "writeObjectsToFile() error:"
                        + e.getMessage());
            }
        }
    }

    /**
     * 将对象写到文件中
     * 
     * @param filename
     * @param obj
     */
    public static void writeObjectToFile(String filename, Object obj) {
        File file = new File(filename);

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            objOutputStream = new ObjectOutputStream(fileOutputStream);
            objOutputStream.writeObject(obj);
        } catch (IOException e) {
            Log.e(FileUtil.class.getSimpleName() + "writeObjectsToFile() error:" + e.getMessage());
        } finally {
            try {
                if (objOutputStream != null) {
                    objOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                Log.e(FileUtil.class.getSimpleName() + "writeObjectsToFile() error:"
                        + e.getMessage());
            }
        }
    }

    /**
     * 从文件中读取缓存的文件对象列表
     * 
     * @param filename
     * @return List<Object>
     * @throws FileNotFoundException
     */
    public static List<Object> readObjectsFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);

        if (!file.exists())
            throw new FileNotFoundException();

        List<Object> list = new ArrayList<Object>();

        ObjectInputStream objInputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            objInputStream = new ObjectInputStream(fileInputStream);

            while (fileInputStream.available() > 0) {
                list.add(objInputStream.readObject());
            }
        } catch (IOException e) {
            Log.e(FileUtil.class.getSimpleName() + "readObjectsFromFile() error:" + e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e(FileUtil.class.getSimpleName() + "readObjectsFromFile() error:" + e.getMessage());
        } finally {
            try {
                if (objInputStream != null) {
                    objInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                Log.e(FileUtil.class.getSimpleName() + "readObjectsFromFile() error:"
                        + e.getMessage());

            }
        }

        return list;
    }

    /**
     * 将对象列表追加到稳重中
     *
     * @param filename
     * @param objs
     * @throws FileNotFoundException
     */
    public static void appendObjectsToFile(String filename, List<Object> objs)
            throws FileNotFoundException {
        File file = new File(filename);
        if (!file.exists())
            throw new FileNotFoundException();

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file, true);
            objOutputStream = new ObjectOutputStream(fileOutputStream) {
                protected void writeStreamHeader()
                        throws IOException {
                }
            };

            for (Object obj : objs) {
                objOutputStream.writeObject(obj);
            }
        } catch (IOException e) {
            Log.e(FileUtil.class.getSimpleName() + "appendObjectsToFile() error:"
                    + e.getMessage());
        } finally {
            try {
                if (objOutputStream != null) {
                    objOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                Log.e(FileUtil.class.getSimpleName() + "appendObjectsToFile() error:"
                        + e.getMessage());
            }
        }
    }

    /**
     * 将对象追加到文件中
     * 
     * @param filename
     * @param obj
     * @throws FileNotFoundException
     */
    public static void appendObjectToFile(String filename, Object obj)
            throws FileNotFoundException {

        File file = new File(filename);
        if (!file.exists())
            throw new FileNotFoundException();

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file, true);
            objOutputStream = new ObjectOutputStream(fileOutputStream) {
                protected void writeStreamHeader()
                        throws IOException {
                }
            };

            objOutputStream.writeObject(obj);
        } catch (IOException e) {
            Log.e(FileUtil.class.getSimpleName() + "appendObjectsToFile() error:"
                    + e.getMessage());
        } finally {
            try {
                if (objOutputStream != null) {
                    objOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                Log.e(FileUtil.class.getSimpleName() + "appendObjectsToFile() error:"
                        + e.getMessage());
            }
        }
    }
	
}