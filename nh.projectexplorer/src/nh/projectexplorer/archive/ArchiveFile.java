/*******************************************************************************
 * Copyright (c) 2011 Bundlemaker project team.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Bundlemaker project team - initial API and implementation
 ******************************************************************************/
package nh.projectexplorer.archive;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class ArchiveFile {

  private final JarFile      _jarFile;

  private List<ArchiveEntry> _contents;

  /**
   * @param jarFile
   */
  public ArchiveFile(JarFile jarFile) {
    super();
    _jarFile = jarFile;

  }

  public String getName() {
    return _jarFile.getName();
  }

  /**
   * @return
   */
  public Object[] getEntries() {
    if (_contents == null) {
      Enumeration<JarEntry> entries = _jarFile.entries();
      _contents = new LinkedList<ArchiveEntry>();

      while (entries.hasMoreElements()) {
        _contents.add(new ArchiveEntry(this, entries.nextElement().getName()));
      }
    }
    return _contents.toArray(new ArchiveEntry[_contents.size()]);
  }

}
