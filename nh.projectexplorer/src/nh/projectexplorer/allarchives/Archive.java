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
package nh.projectexplorer.allarchives;

import nh.projectexplorer.archive.ArchiveFile;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class Archive {

  private AllArchives       _parent;

  private final ArchiveFile _archiveFile;

  /**
   * @param parent
   * @param archiveFile
   */
  public Archive(AllArchives parent, ArchiveFile archiveFile) {
    super();
    _parent = parent;
    _archiveFile = archiveFile;
  }

  /**
   * @return the parent
   */
  public AllArchives getParent() {
    return _parent;
  }

  /**
   * @param parent
   *          the parent to set
   */
  public void setParent(AllArchives parent) {
    _parent = parent;
  }

  /**
   * @return the archiveFile
   */
  public ArchiveFile getArchiveFile() {
    return _archiveFile;
  }

  public String toString() {
    return _archiveFile.getName();
  }

}
