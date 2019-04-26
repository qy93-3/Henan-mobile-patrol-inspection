package com.aaa.project.tool.build;

import java.io.*;

/**
 * @anthor TimeLordTTY
 * @Create 2019-04-25 15:32
 */
public class SerialCloneable implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    //深拷贝
    public Object clone()
    {
        try
        {
            // save the object to a byte array
            //将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(this);
            out.close();
            // read a clone of the object from the byte array
            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bin);
            Object ret = in.readObject();
            in.close();

            return ret;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
