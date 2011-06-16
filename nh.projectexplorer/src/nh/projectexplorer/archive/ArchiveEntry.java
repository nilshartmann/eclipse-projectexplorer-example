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

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class ArchiveEntry {

  private final ArchiveFile _file;

  private final String      _name;

  /**
   * @param file
   * @param name
   */
  public ArchiveEntry(ArchiveFile file, String name) {
    super();
    _file = file;
    _name = name;
  }

  /**
   * @return the file
   */
  public ArchiveFile getFile() {
    return _file;
  }

  /**
   * @return the name
   */
  public String getName() {
    return _name;
  }

  public boolean isFolder() {
    return _name.endsWith("/");
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_file == null) ? 0 : _file.hashCode());
    result = prime * result + ((_name == null) ? 0 : _name.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ArchiveEntry other = (ArchiveEntry) obj;
    if (_file == null) {
      if (other._file != null)
        return false;
    } else if (!_file.equals(other._file))
      return false;
    if (_name == null) {
      if (other._name != null)
        return false;
    } else if (!_name.equals(other._name))
      return false;
    return true;
  }

  public String toString() {
    return getName();
  }

}
