package com.sinaapp.whutec.util.common;

import java.io.File;

/**
 * @author keshuangjie
 * @date 2014-12-2 下午3:43:25
 * 文件处理工具类
 */
public class FileUtil {
	
	/**
	 *  获取文件后缀
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
	 *  获取文件后缀
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
     * 写入对象列表到指定文件
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
            MecpLogUtil.e(MecpStatisticsManage.class.getSimpleName() + "writeObjectsToFile() error:" + e.getMessage());
        } finally {
            try {
                if (objOutputStream != null) {
                    objOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                MecpLogUtil.e(MecpStatisticsManage.class.getSimpleName() + "writeObjectsToFile() error:"
                        + e.getMessage());
            }
        }
    }

    /**
     * 写入对象到指定文件
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
            MecpLogUtil.e(MecpStatisticsManage.class.getSimpleName() + "writeObjectsToFile() error:" + e.getMessage());
        } finally {
            try {
                if (objOutputStream != null) {
                    objOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                MecpLogUtil.e(MecpStatisticsManage.class.getSimpleName() + "writeObjectsToFile() error:"
                        + e.getMessage());
            }
        }
    }

    /**
     * 从文件中读取对象
     * 
     * @param filename
     * @return 返回对象列表
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
            MecpLogUtil.e(MecpStatisticsManage.class.getSimpleName() + "readObjectsFromFile() error:" + e.getMessage());
        } catch (ClassNotFoundException e) {
            MecpLogUtil.e(MecpStatisticsManage.class.getSimpleName() + "readObjectsFromFile() error:" + e.getMessage());
        } finally {
            try {
                if (objInputStream != null) {
                    objInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                MecpLogUtil.e(MecpStatisticsManage.class.getSimpleName() + "readObjectsFromFile() error:"
                        + e.getMessage());

            }
        }

        return list;
    }

    /**
     * 合并对象列表到指定文件
     * 
     * @param objs
     * @param filename
     * @throws FileNotFoundException
     */
    public static void appendObjectsToFile(List<Object> objs, String filename)
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
            MecpLogUtil.e(MecpStatisticsManage.class.getSimpleName() + "appendObjectsToFile() error:"
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
                MecpLogUtil.e(MecpStatisticsManage.class.getSimpleName() + "appendObjectsToFile() error:"
                        + e.getMessage());
            }
        }
    }

    /**
     * 合并对象到指定文件
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
            MecpLogUtil.e(MecpStatisticsManage.class.getSimpleName() + "appendObjectsToFile() error:"
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
                MecpLogUtil.e(MecpStatisticsManage.class.getSimpleName() + "appendObjectsToFile() error:"
                        + e.getMessage());
            }
        }
    }
	
}
