package com.example.backend.service;

import com.example.backend.entity.TagsEntity;
import com.example.backend.model.ItemModel;
import com.example.backend.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagsService {

    private final TagsRepository tagsRepository;

    @Autowired
    public TagsService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    public List<TagsEntity> getAll(){
        return tagsRepository.findAll();
    }

    public Set<TagsEntity> findTagsByMame(String[] tags) {
        Set<TagsEntity> tagSet = new HashSet<>();
        for (String tag: tags) {
            TagsEntity tagsEntity = tagsRepository.findByTag(tag);
            if (tagsEntity==null){
               TagsEntity e= tagsRepository.save(new TagsEntity(tag));
               tagSet.add(e);
            }else {
                tagSet.add(tagsEntity);
            }
        }
        return tagSet;
    }

    public void saveItemTags(Integer itemId, Integer tagId) {
        tagsRepository.itemTags(itemId, tagId);
    }

    public Set<TagsEntity> findTagsByItemId(int id) {
        return tagsRepository.findAllByItemId(id);
    }

    public void checkTags(ItemModel item) throws Exception {
        if (item.getTags().length==0){
            throw new Exception("Tags cannot be empty");
        }else {
            item.getTagSet().forEach(s-> tagsRepository.deleteAllByItem(item.getId()));
            findTagsByMame(item.getTags()).forEach(s -> saveItemTags(item.getId(),s.getId()));
        }

    }
}
