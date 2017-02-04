package com.armaturemc.modelcreator.config;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Andrew Tran
 */
public class AMCConfig<T> {
    File file;
    T object;
    Class<T> objectClass;
    Yaml yaml = ArmatureModelCreator.getInstance().getYaml();
    public AMCConfig(File file, Class<T> objectClass){
        this.file = file;
        this.objectClass = objectClass;
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void read() throws FileNotFoundException {
        object = yaml.loadAs(new FileReader(file), objectClass);
    }

    public T getConfigObject(){
        return object;
    }

    public void save() throws IOException {
        FileUtils.write(file, yaml.dump(object), "UTF-8");
    }
}
