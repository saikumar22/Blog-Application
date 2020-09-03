package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface {
	
	private static final String Inset_sql="insert into blog(blogid,blogtitle,blogdescription,postedon)values(seq_blog.nextval,?,?,?)";
	private static final String select_all="select * from blog";
	private static final String delete_blog="delete from blog where blogid=?";
	
	@Override
	public void insertBlog(Blog blog) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(Inset_sql);
		try
		{
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement(Inset_sql);
			//ps.setInt(1, blog.getBlogId());
			ps.setString(1, blog.getBlogTitle());
			ps.setString(2, blog.getBlogDescription());
			ps.setDate(3, java.sql.Date.valueOf(blog.getPostedOn()));
			System.out.println(ps);
			ps.executeUpdate();
			
			
		}
		catch(SQLException e)
		{
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Blog selectBlog(int blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blog> selectALLBlogs() {
		Blog blog=null;
		List<Blog> bloglist=new ArrayList<>();
		try
		{
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement(select_all);
			System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int id=rs.getInt(1);
				String blogtitle=rs.getString(2);
				String blogdescription=rs.getString(3);
				LocalDate postedOn=rs.getDate(4).toLocalDate();
				blog=new Blog();
				blog.setBlogId(id);
				blog.setBlogTitle(blogtitle);
				blog.setBlogDescription(blogdescription);
				blog.setPostedOn(postedOn);
				bloglist.add(blog);
			}
			
		}
		catch(SQLException e)
		{
			System.out.println(e);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bloglist;
	}

	@Override
	public boolean deleteBlog(int id) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(id);
		boolean rowdeleted = false;
		try {
			Connection con=ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement(delete_blog);
			ps.setInt(1, id);
			rowdeleted=ps.executeUpdate()>0;
			
			
		}
		catch(SQLException e)
		{
			System.out.println(e);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowdeleted;
	}

	@Override
	public boolean updateBlog(Blog blog) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Blog> selectAllBlogs() {
		// TODO Auto-generated method stub
		return null;
	}

}


