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

import java.util.HashSet;
import java.util.jar.JarFile;

import nh.projectexplorer.archive.ArchiveFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class AllArchives {

  private final IWorkspaceRoot _workspaceRoot;

  private HashSet<Archive>     _archives;

  /**
   * @param workspaceRoot
   * @param allArchiveFiles
   */
  public AllArchives(IWorkspaceRoot workspaceRoot, HashSet<IFile> allArchiveFiles) {
    _workspaceRoot = workspaceRoot;

    _archives = new HashSet<Archive>();

    for (IFile iFile : allArchiveFiles) {
      try {
        _archives.add(new Archive(this, new ArchiveFile(new JarFile(iFile.getLocation().makeAbsolute().toFile()))));
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

  }

  public IWorkspaceRoot getWorkspaceRoot() {
    return _workspaceRoot;
  }

  public Object[] getArchives() {
    return _archives.toArray(new Archive[_archives.size()]);
  }

  public String toString() {
    return _archives.size() + " Archives in the workspace";
  }
}
