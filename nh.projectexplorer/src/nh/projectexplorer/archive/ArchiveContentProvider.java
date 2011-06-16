package nh.projectexplorer.archive;

import java.util.Hashtable;
import java.util.Map;
import java.util.jar.JarFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ArchiveContentProvider implements ITreeContentProvider {

  private final Map<IFile, ArchiveFile> _archives = new Hashtable<IFile, ArchiveFile>();

  @Override
  public void dispose() {

    // TDO
    _archives.clear();

  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // TODO Auto-generated method stub

  }

  @Override
  public Object[] getElements(Object inputElement) {

    return getChildren(inputElement);
  }

  /**
   * @param file
   * @return
   */
  private ArchiveFile getOrCreateArchiveFile(IFile file) {
    try {
      ArchiveFile archiveFile = _archives.get(file);
      if (archiveFile == null) {
        JarFile jarFile = new JarFile(file.getLocation().makeAbsolute().toFile());
        archiveFile = new ArchiveFile(jarFile);
        _archives.put(file, archiveFile);
      }
      return archiveFile;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof IFile) {
      IFile file = (IFile) parentElement;
      ArchiveFile archiveFile = getOrCreateArchiveFile(file);
      return archiveFile.getEntries();
    }

    return null;
  }

  @Override
  public Object getParent(Object element) {
    return null;
    // if (element instanceof ArchiveEntry) {
    // ArchiveEntry archiveEntry = (ArchiveEntry) element;
    // return archiveEntry.getFile();
    // }
  }

  @Override
  public boolean hasChildren(Object element) {
    return (element instanceof IFile);
  }

}
