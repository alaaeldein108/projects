%first line
connected(firstline,[new_elmarg,elmarg,
           elmarg,ezbet_elnakhl,
           ezbet_elnakhl,ain_shams,
           ain_shams,elmatareyya,
           elmatareyya,helmeyet_elzaitoun,
           helmeyet_elzaitoun,hadayeq_elzaitoun,
           hadayeq_elzaitoun,saray_elqobba,
           saray_elqobba,hammamat_elqobba,
           hammamat_elqobba,kobri_elqobba,
           kobri_elqobba,manshiet_elsadr,
           manshiet_elsadr,eldemerdash,
           eldemerdash,ghamra,
           ghamra,alshohadaa,
           alshohadaa,urabi,nasser,sadat,
           sadat,saad_zaghloul,
           saad_zaghloul, alsayyeda_zeinab,
           alsayyeda_zeinab,elmalek_elsaleh,
           elmalek_elsaleh,margirgis,
           margirgis,elzahraa,
           elzahraa,dar_elsalam,
           dar_elsalam,hadayeq_elmaadi,
           hadayeq_elmaadi,maadi,
           maadi,thakanat_elmaadi,
           thakanat_elmaadi,tora_elbalad,
           tora_elbalad,kozzika,
           kozzika,tora_elasmant,
           tora_elasmant,elmaasara,
           elmaasara,hadayeq_helwan,
           hadayeq_helwan,wadi_hof,
           wadi_hof,helwan_university,
           helwan_university,ain_helwan,
           ain_helwan,helwan]).
%second line
connected(secondline,[shobra_elkheima,koliet_elzeraa,
                 koliet_elzeraa,mezallat,
                 mezallat,khalafawy,
                 khalafawy,sainte_teresa,
                 sainte_teresa,road_elfarag,
                 road_elfarag,massara,
                 massara,alshohadaa,
                 alshohadaa,ataba,
                 ataba,naguib,
                 naguib,sadat,
                 sadat,opera,
                 opera,dokki,
                 dokki,bohooth,
                 bohooth,cairo_university,
                 cairo_university,faisal,
                 faisal,giza,
                 giza,omm_elmisryeen,
                 omm_elmisryeen,sakiat_mekki,
                 sakiat_mekki,elmounib]).




findRoute(X,Y):-findRoute(X,Y,[],[]).
findRoute(X,Y,Lines,Output):-connected(Line,Stations),\+ member(Line,Lines),member(X,Stations),member(Y,Stations),append(Output,[[X,Line,Y]],NewOutput),print(NewOutput).

findRoute(X,Y,Lines,Output):-connected(Line,Stations),\+ member(Line,Lines),member(X,Stations),member(Intermediate,Stations),X\=Intermediate,Intermediate\=Y,append(Output,[[X,Line,Intermediate]],NewOutput),findRoute(Intermediate,Y,[Line|Lines],NewOutput).
print([]).
print([H|T]):-format('from ~w take ~w line to ~w\n', H),print(T).






