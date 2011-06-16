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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class AllArchivesContentProvider implements ITreeContentProvider {

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IContentProvider#dispose()
   */
  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object,
   * java.lang.Object)
   */
  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
   */
  @Override
  public Object[] getElements(Object inputElement) {
    return getChildren(inputElement);

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
   */
  @Override
  public Object[] getChildren(Object parentElement) {

    if (parentElement instanceof IWorkspaceRoot) {
      IWorkspaceRoot workspaceRoot = (IWorkspaceRoot) parentElement;
      HashSet<IFile> allArchiveFiles = new HashSet<IFile>();

      IProject[] projects = workspaceRoot.getProjects();
      for (IProject iProject : projects) {
        try {
          if (iProject.isOpen()) {
            System.out.println("Adding archives from: " + iProject);
            addArchives(allArchiveFiles, iProject);
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }

      AllArchives allArchives = new AllArchives(workspaceRoot, allArchiveFiles);

      return new Object[] { allArchives };
    }

    if (parentElement instanceof AllArchives) {
      return ((AllArchives) parentElement).getArchives();
    }

    if (parentElement instanceof Archive) {
      return ((Archive) parentElement).getArchiveFile().getEntries();
    }
    return null;
  }

  /**
   * @param allArchives
   * @param iProject
   * @throws CoreException
   */
  private void addArchives(HashSet<IFile> allArchives, IResource resource) throws CoreException {
    if (resource instanceof IFile) {
      if (!resource.exists()) {
        return;
      }
      IFile file = (IFile) resource;
      if (file.getName().endsWith(".zip")) {
        allArchives.add(file);
        return;
      }
    }

    if (resource instanceof IContainer) {
      IContainer container = (IContainer) resource;
      IResource[] members = container.members();
      for (IResource iResource : members) {
        addArchives(allArchives, iResource);
      }
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
   */
  @Override
  public Object getParent(Object element) {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
   */
  @Override
  public boolean hasChildren(Object element) {
    return getChildren(element) != null;
  }

}
