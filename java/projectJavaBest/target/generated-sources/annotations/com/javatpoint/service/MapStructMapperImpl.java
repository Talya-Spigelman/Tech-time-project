package com.javatpoint.service;

import com.javatpoint.dto.PostDTO;
import com.javatpoint.model.Post;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-21T18:54:53+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_402 (IBM Corporation)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public List<PostDTO> postToDTO(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostDTO> list = new ArrayList<PostDTO>( posts.size() );
        for ( Post post : posts ) {
            try {
                list.add( PostToDTO( post ) );
            }
            catch ( IOException e ) {
                throw new RuntimeException( e );
            }
        }

        return list;
    }
}
