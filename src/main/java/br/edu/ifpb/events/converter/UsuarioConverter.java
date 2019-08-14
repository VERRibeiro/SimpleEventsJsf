//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.FacesConverter;
//
//import br.edu.ifpb.events.entity.Usuario;
//
//@FacesConverter("UsuarioConverter")
//public class UsuarioConverter  extends SelectItemsBaseConverter{
//
//	@Override
//	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		if (value != null && value instanceof Usuario)
//			return ((Usuario) value).getId().toString();
//		else
//			return null;
//	}
//
//}