package nh.projectexplorer.archive;

import nh.projectexplorer.UIImages;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class ArchiveLabelProvider implements ILabelProvider {

  @Override
  public void addListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof ArchiveEntry) {
      ArchiveEntry archiveEntry = (ArchiveEntry) element;
      if (archiveEntry.isFolder()) {
        return UIImages.FOLDER_ICON.getImage();
      }
      return UIImages.FILE_ICON.getImage();
    }
    return null;
  }

  @Override
  public String getText(Object element) {
    return String.valueOf(element);
  }

}
