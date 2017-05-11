/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformgame;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Mateusz
 */
public interface IOOperation {

    public void Save(File file);

    public void Load(File file);

    public void Serialize(ObjectOutputStream out);

    public void Deserialize(ObjectInputStream in);

}
